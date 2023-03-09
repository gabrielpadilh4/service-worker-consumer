package io.github.gabrielpadilh4.configuration;

public final class MessageBrokerConfiguration {
    public static final String BROKER_HOST = System.getProperty("BROKER_HOST", "localhost");
    public static final String BROKER_USERNAME = System.getProperty("BROKER_USERNAME", "guest");
    public static final String BROKER_PASSWORD = System.getProperty("BROKER_PASSWORD", "guest");
}
