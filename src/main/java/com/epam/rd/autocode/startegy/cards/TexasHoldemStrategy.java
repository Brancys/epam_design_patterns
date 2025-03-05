package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class TexasHoldemStrategy implements CardDealingStrategy {

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> hands = new LinkedHashMap<>();
        
        // Crear listas para cada jugador
        for (int i = 1; i <= players; i++) {
            hands.put("Player " + i, new ArrayList<>());
        }

        // Crear la lista de cartas comunitarias
        hands.put("Community", new ArrayList<>());

        // Repartir 2 cartas a cada jugador
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= players; j++) {
                hands.get("Player " + j).add(deck.dealCard());
            }
        }

        // Repartir 5 cartas comunitarias
        for (int i = 0; i < 5; i++) {
            hands.get("Community").add(deck.dealCard());
        }

        // Cartas restantes
        hands.put("Remaining", deck.restCards());

        return hands;
    }
}
