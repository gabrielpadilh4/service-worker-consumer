package io.github.gabrielpadilh4.queues;

public enum RabbitMQQueues {

    TEST_QUEUE("test-queue");

    private final String queueName;
    RabbitMQQueues(String queueName) {
        this.queueName = queueName;
    }
}
