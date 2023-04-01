package com.test.technique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.test.technique.Score.ZERO;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        LOGGER.info("Hello world!\n This is a tennis score computer");

        final TennisGame tennisGame = new TennisGame();
        tennisGame.play();
    }
}