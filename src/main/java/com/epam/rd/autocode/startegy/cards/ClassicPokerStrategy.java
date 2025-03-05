package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class ClassicPokerStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> result = new HashMap<>();

        // Crear listas para cada jugador
        for (int i = 1; i <= players; i++) {
            result.put("Player " + i, new ArrayList<>());
        }

        // Repartir 5 cartas a cada jugador, una por ronda
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= players; j++) {
                result.get("Player " + j).add(deck.dealCard());
            }
        }

        // Guardar cartas restantes
        result.put("Remaining", deck.restCards());

        return result;
    }
}
