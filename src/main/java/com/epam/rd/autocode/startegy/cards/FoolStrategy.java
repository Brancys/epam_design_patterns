package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class FoolStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> result = new HashMap<>();

        // Crear listas para cada jugador
        for (int i = 1; i <= players; i++) {
            result.put("Player " + i, new ArrayList<>());
        }

        // Cada jugador recibe 6 cartas
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= players; j++) {
                result.get("Player " + j).add(deck.dealCard());
            }
        }

        // La siguiente carta es la "Trump card"
        List<Card> trumpCard = new ArrayList<>();
        trumpCard.add(deck.dealCard());
        result.put("Trump card", trumpCard);

        // El resto de cartas se consideran "Remaining"
        result.put("Remaining", deck.restCards());

        return result;
    }
}
