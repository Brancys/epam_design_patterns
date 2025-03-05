package com.epam.rd.autocode.startegy.cards;

public class CardDealingStrategies {

    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new TexasHoldemStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPokerStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new BridgeStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new FoolStrategy();
    }

    private CardDealingStrategies() {
        throw new UnsupportedOperationException("Utility class");
    }

}
