package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class BridgeStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> result = new HashMap<>();

        // Bridge siempre se juega con 4 jugadores
        if (players != 4) {
            throw new IllegalArgumentException("Bridge requires exactly 4 players.");
        }

        // Crear listas para cada jugador
        for (int i = 1; i <= players; i++) {
            result.put("Player " + i, new ArrayList<>());
        }

        // Repartir 13 cartas a cada jugador
        for (int i = 0; i < 13; i++) {
            for (int j = 1; j <= players; j++) {
                result.get("Player " + j).add(deck.dealCard());
            }
        }

        return result;
    }
}
