package io.github.gabrielpadilh4;

import io.github.gabrielpadilh4.consumers.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting Message Broker Consumer Service...");
        TestListener.receiveMessage();
    }
}