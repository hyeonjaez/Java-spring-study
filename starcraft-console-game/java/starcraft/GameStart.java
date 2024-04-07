package starcraft;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import starcraft.exception.CanNotAttack;
import starcraft.player.Computer;
import starcraft.player.Player;
import starcraft.player.User;
import starcraft.tribe.Protoss;
import starcraft.tribe.Terran;
import starcraft.tribe.Tribe;
import starcraft.tribe.Zerg;

public class GameStart {
    private User user;
    private Computer computer;
    private Judge judge;

    public void flowGame() {
        Scanner scanner = new Scanner(System.in);
        this.judge = new Judge();
        printStart();
        printChooseTribe(scanner);
        playGame(scanner);
        printWhoWin();

    }

    private void printStart() {
        System.out.println("================================");
        System.out.println("== 스타크래프트 게임을 시작하겠습니다. ==");
        System.out.println("================================");
    }

    private void printChooseTribe(Scanner scanner) {
        System.out.println("종족을 고르세요");
        System.out.println("테란 :    1번");
        System.out.println("프로토스 : 2번");
        System.out.println("저그 :    3번");
        chooseTribe(scanner);
    }

    private void chooseTribe(Scanner scanner) {
        try {
            int chooseNumber = scanner.nextInt();
            if (!verifyChooseNumber(chooseNumber)) {
                System.out.println("잘못된 입력입니다 다시 고르세요");
                scanner.nextLine();
                printChooseTribe(scanner);
            } else {
                scanner.nextLine();
                this.user = new User(produceTribeUnit(chooseNumber));
                this.computer = new Computer(randomComputerTribe());
            }

        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다 다시 고르세요");
            printChooseTribe(scanner);
        }
    }

    private boolean verifyChooseNumber(int inputNumber) {
        return inputNumber == 1 || inputNumber == 2 || inputNumber == 3;
    }

    private Tribe randomComputerTribe() {
        Random random = new Random();
        int computerNumber = random.nextInt(3) + 1;
        return produceTribeUnit(computerNumber);
    }

    private Tribe produceTribeUnit(int number) {
        if (number == 1) {
            return new Terran();
        } else if (number == 2) {
            return new Protoss();
        } else {
            return new Zerg();
        }
    }

    private void printUnitStatus() {
        System.out.println("적군 :" + this.computer.getTribe().getTribeName());
        this.computer.printMyUnit();
        System.out.println("아군 :" + this.user.getTribe().getTribeName());
        this.user.printMyUnit();
    }

    private void chooseAttackUnit(Scanner scanner) {
        try {

            System.out.println("공격을 수행할 아군 유닛과 공격할 적군 유닛을 선택하세요:");
            System.out.print("공격을 수행할 아군 : ");
            int friendlyNumber = scanner.nextInt();
            verifySelectNumber(this.user, friendlyNumber);


            System.out.print("공격할 적군 : ");
            int enemyNumber = scanner.nextInt();
            verifySelectNumber(this.computer, enemyNumber);

            attackPlayer(this.user, this.computer, friendlyNumber, enemyNumber);
        } catch (InputMismatchException | IllegalArgumentException | NullPointerException | CanNotAttack e) {

            System.out.println("다시 고르세요");
            chooseAttackUnit(scanner);
        }
    }

    private void verifySelectNumber(Player player, int number) {
        if (!(0 <= number && number < player.getTribe().getUnitList().size())) {
            throw new IllegalArgumentException();
        }
    }

    private void playGame(Scanner scanner) {
        while (!(this.judge.isGameQuit(this.user)) && !(this.judge.isGameQuit(this.computer))) {
            printUnitStatus();
            chooseAttackUnit(scanner);
            this.computer.deleteDieUnit();

            if (judge.isGameQuit(this.computer)) {
                break;
            }

            attackComputer();
            this.user.deleteDieUnit();
        }

    }

    private void printWhoWin() {
        judge.printWin(this.user, this.computer);
    }

    private void attackPlayer(Player player, Player target, int friendlyNumber, int enemyNumber) throws CanNotAttack {
        player.attack(target.getTribe().getUnitList().get(enemyNumber), friendlyNumber);
    }

    private void attackComputer() {
        try {
            int friendlyNumber = this.computer.chooseRandomNumber(computer.getTribe());
            int enemyNumber = this.computer.chooseRandomNumber(user.getTribe());
            attackPlayer(this.computer, this.user, friendlyNumber, enemyNumber);
        } catch (CanNotAttack e) {
            attackComputer();
        }
    }
}
