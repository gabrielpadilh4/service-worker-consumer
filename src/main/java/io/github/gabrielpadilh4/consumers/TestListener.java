package io.github.gabrielpadilh4.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import io.github.gabrielpadilh4.factory.BrokerConnectionFactory;
import io.github.gabrielpadilh4.queues.RabbitMQQueues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class TestListener {

    private static Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

    public static void receiveMessage() throws IOException {

        Connection connection = BrokerConnectionFactory.getInstance().getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(String.valueOf(RabbitMQQueues.TEST_QUEUE), true, false, false, null);
        LOGGER.info(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            LOGGER.info(" [x] Received '" + message + "'");

            try {
                doWork(message);
            } finally {
                LOGGER.info(" [x] Done '" + message + "'");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        channel.basicConsume(String.valueOf(RabbitMQQueues.TEST_QUEUE), false, deliverCallback, consumerTag -> {});
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
