package com.nhnacademy.jaehyeon.exercise5_4and5;

import java.util.Scanner;

public class PlayGame {
    public PlayGame() {
    }

    static final int START_CARD_NUMBER = 2;

    public void play() {

        Deck deck = new Deck();
        BlackjackHand user = new BlackjackHand("User");
        BlackjackHand dealer = new BlackjackHand("Dealer");

        PrintMessage.printStartGame();
        deck.shuffle();

        PrintMessage.printUserGiveCard();
        giveCard(user, deck, START_CARD_NUMBER);
        PrintMessage.printValue(user);

        PrintMessage.printDealerGiveCard();
        giveCard(dealer, deck, START_CARD_NUMBER);
        PrintMessage.printDealerOneCard(dealer);

        while (!isGameQuit(user) && !isGameQuit(dealer)) {


            PrintMessage.printAskHitOrStand();
            int selectStandOrHit = selectUserStandOrHit();

            if (isNumberOver16(dealer) && isDealerHaveCardNumber(dealer)) {
                PrintMessage.printGiveDealerOneCard(dealer);
                giveOneCard(dealer, deck);
            }

            if (selectStandOrHit == 1) {
                break;
            } else {
                giveOneCard(user, deck);
                PrintMessage.printValue(user);
            }
        }
        judgementGameWinner(user, dealer);
        PrintMessage.printTotalCardValue(user);
        PrintMessage.printTotalCardValue(dealer);
    }


    private void giveCard(BlackjackHand blackjackHand, Deck deck, int number) {
        for (int i = 0; i < number; i++) {
            blackjackHand.addCard(deck.dealCard());
        }
    }

    private boolean isNumberOver16(BlackjackHand dealer) {
        return dealer.getBlackjackValue() <= 16;
    }

    private boolean isDealerHaveCardNumber(BlackjackHand dealer) {
        return dealer.getDealerHaveCard() <= 2;
    }

    private void giveOneCard(BlackjackHand blackjackHand, Deck deck) {
        blackjackHand.addCard(deck.dealCard());
    }

    private boolean isGameQuit(BlackjackHand blackjackHand) {
        return blackjackHand.getBlackjackValue() >= 21;
    }

    private int selectUserStandOrHit() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        verifyInputNumber(num);

        return num;
    }

    private void verifyInputNumber(int num) {
        if (num != 1 && num != 2) {
            throw new IllegalArgumentException("1 또는 2를 선택 해주세요");
        }
    }

    private void judgementGameWinner(BlackjackHand user, BlackjackHand dealer) {
        if (dealer.getBlackjackValue() == 21) {
            System.out.println("딜러 블랙잭");
            System.out.println("=========게임 종료=========");
            System.out.println("딜러 승리");

        } else if (user.getBlackjackValue() == 21) {
            System.out.println("유저 블랙잭");
            System.out.println("=========게임 종료=========");
            System.out.println("user 승리");

        } else if (dealer.getBlackjackValue() > 21) {
            System.out.println("딜러 버스트");
            System.out.println("=========게임 종료=========");
            System.out.println("user 승리");

        } else if (user.getBlackjackValue() > 21) {
            System.out.println("유저 버스트");
            System.out.println("=========게임 종료=========");
            System.out.println("dealer 승리");

        } else if (user.getBlackjackValue() == dealer.getBlackjackValue()) {
            System.out.println("=========게임 종료=========");
            System.out.println("dealer 승리");

        } else if (user.getBlackjackValue() > dealer.getBlackjackValue()) {
            System.out.println("=========게임 종료=========");
            System.out.println("user 승리");

        } else if (dealer.getBlackjackValue() > user.getBlackjackValue()) {
            System.out.println("=========게임 종료=========");
            System.out.println("dealer 승리");
        }
    }

    // 게임을 시작해

    // 딜러랑 유저한테 카드를 2장 나눠줘

    // 딜러는 카드를 하나 오픈해

    // 딜러는 16이하 이면 카드를 하나 뽑아

    // 유저하는 stand hit 결정하기

    // hit 했는데 21 넘어가면 끝

    // 안넘어가면 결정

    //stand 하면 비교하고 게임 끝

}
