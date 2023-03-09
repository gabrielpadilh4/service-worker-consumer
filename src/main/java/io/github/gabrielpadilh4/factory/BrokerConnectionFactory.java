package io.github.gabrielpadilh4.factory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import io.github.gabrielpadilh4.configuration.MessageBrokerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BrokerConnectionFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(BrokerConnectionFactory.class);

    private static BrokerConnectionFactory instance;

    private Connection connection;

    private BrokerConnectionFactory() {
        LOGGER.debug("Creating new instance of broker factory.");
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(MessageBrokerConfiguration.BROKER_HOST);
        factory.setUsername(MessageBrokerConfiguration.BROKER_USERNAME);
        factory.setPassword(MessageBrokerConfiguration.BROKER_PASSWORD);

        try {
            this.connection = factory.newConnection();
        } catch (Exception e) {
            LOGGER.debug("Failed to get connection of Broker Factory. Stacktrace: " + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static BrokerConnectionFactory getInstance() {
        LOGGER.debug("Checking broker factory instance.");

        if (instance == null) {
            LOGGER.debug("Creating new instance of broker factory.");
            instance = new BrokerConnectionFactory();
        } else if (!instance.getConnection().isOpen()) {
            LOGGER.debug("Broker factory connection is closed. Creating new instance.");
            instance = new BrokerConnectionFactory();
        }

        LOGGER.debug("Returning broker factory instance.");

        return instance;
    }
}
