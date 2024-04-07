package com.nhnacademy.jaehyeon.exercise5_4and5;

public class BlackjackHand extends Hand {
    private String name;

    public BlackjackHand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBlackjackValue() {

        int val;
        boolean ace;
        int cards;

        val = 0;
        ace = false;
        cards = getCardCount();

        for (int i = 0; i < cards; i++) {

            Card card;
            int cardVal;
            card = getCard(i);
            cardVal = card.getValue();
            if (cardVal > 10) {
                cardVal = 10;
            }
            if (cardVal == 1) {
                ace = true;
            }
            val = val + cardVal;
        }


        if (ace == true && val + 10 <= 21)
            val = val + 10;

        return val;

    }

}