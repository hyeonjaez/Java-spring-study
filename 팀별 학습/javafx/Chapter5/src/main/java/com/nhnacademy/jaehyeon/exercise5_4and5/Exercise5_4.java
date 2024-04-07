package com.nhnacademy.jaehyeon.exercise5_4and5;

import java.util.Scanner;

public class Exercise5_4 {

    public static void main(String[] args) {

        int cardNumber = selectCardNumber();
        Deck deck = new Deck();
        BlackjackHand blackjackHand = new BlackjackHand("User");
        deck.shuffle();

        for (int i = 0; i < cardNumber; i++) {
            blackjackHand.addCard(deck.dealCard());
        }

        blackjackHand.printCard();

        System.out.println("총 카드의 vlalue: " + blackjackHand.getBlackjackValue());

    }

    public static int selectCardNumber() {
        String selectNumber;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            selectNumber = inputNumber(scanner);
            if (!isInputNumber(selectNumber)) {
                System.out.println("숫자만 입력하세요");
            } else if (!isBetween2And6(selectNumber)) {
                System.out.println("2이상 6이하의 숫자만 입력하세요");
            } else {
                scanner.close();
                break;
            }
        }
        return Integer.parseInt(selectNumber);
    }

    public static String inputNumber(Scanner scanner) {
        System.out.print("몇 장 뽑으실 건가요?: ");
        return scanner.nextLine();
    }


    public static boolean isBetween2And6(String number) {
        return 2 <= Integer.parseInt(number) && Integer.parseInt(number) <= 6;
    }

    public static boolean isInputNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException num) {
            return false;
        }
    }


}

