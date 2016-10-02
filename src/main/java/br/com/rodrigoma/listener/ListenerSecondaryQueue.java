package br.com.rodrigoma.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ListenerSecondaryQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerSecondaryQueue.class);

    //@formatter:off
    @RabbitListener(bindings = {
            @QueueBinding(
                    value    = @Queue(value = "tech.talk.secondary", durable = "true", autoDelete = "false", exclusive = "false"),
                    exchange = @Exchange(value = "moip", type = "topic", durable = "true"),
                    key      = "tech.talk.secondary.example")
    })
    @SendTo("moip/tech.talk.main.example")
    //@formatter:on
    public String secondQueue(Message message) {
        String json = new String(message.getBody());
        LOGGER.info("SECONDARY QUEUE: {}", json);

        return json;
    }
}