package com.test.technique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("This is a tennis score computer");

        final TennisGame tennisGame = new TennisGame();
        tennisGame.play();
    }
}