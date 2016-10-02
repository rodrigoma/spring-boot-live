package br.com.rodrigoma.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerMainQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerMainQueue.class);

    //@formatter:off
    @RabbitListener(bindings = {
            @QueueBinding(
                    value    = @Queue(value = "tech.talk.main", durable = "true", autoDelete = "false", exclusive = "false"),
                    exchange = @Exchange(value = "moip", type = "topic", durable = "true"),
                    key      = "tech.talk.main.example")
    })
    //@formatter:on
    public void mainQueue(Message message) {
        String json = new String(message.getBody());
        LOGGER.info("MAIN QUEUE: {}", json);
    }
}