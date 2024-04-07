package com.nhnacademy.jaehyeon.exercise5_4and5;

public class PrintMessage {
    private PrintMessage() {
    }

    public static void printStartGame() {
        System.out.println("게임을 시작합니다");
        System.out.println("각자 카드를 나눠 주겠습니다.");
    }

    public static void printUserGiveCard() {
        System.out.println("user 카드 나눠 주겠습니다. 확인하세요");
    }

    public static void printCardList() {

    }

    public static void printValue(BlackjackHand blackjackHand) {
        System.out.println(blackjackHand.getName() + " 카드 목록");
        blackjackHand.printCard();
        System.out.printf("현재 %s 카드의 총 value: %d \n", blackjackHand.getName(), blackjackHand.getBlackjackValue());
    }

    public static void printDealerGiveCard() {
        System.out.println("dealer 카드 나눠 주겠습니다. 확인하고 한장 오픈하세요");
    }

    public static void printTotalCardValue(BlackjackHand blackjackHand) {
        System.out.println(blackjackHand.getName() + "의 카드 총 value: " + blackjackHand.getBlackjackValue());
    }

    public static void printGiveDealerOneCard(BlackjackHand dealer) {
        System.out.println(dealer.getName() + " 에게 카드 한장을 주겠습니다.");
    }

    public static void printDealerOneCard(BlackjackHand dealer) {
        dealer.printOneCard();
    }

    public static void printAskHitOrStand() {
        System.out.println("stand 하실건가요 hit 하실건가요?");
        System.out.println("stand : 1 || hit : 2 입니다 눌러주세요");
    }

    public static void printWhoWin(BlackjackHand blackjackHand) {
        System.out.println(blackjackHand.getName() + " 승리");
    }

    public static void printGameQuit() {
        System.out.println("=========게임 종료=========");
    }

    public static void printWhoBlackjack(BlackjackHand blackjackHand) {
        System.out.println(blackjackHand.getName() + " 블랙잭");
    }

    public static void printWhoBust(BlackjackHand blackjackHand) {
        System.out.println(blackjackHand.getName() + " 버스트");
    }


}
