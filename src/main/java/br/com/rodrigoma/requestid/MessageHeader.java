package br.com.rodrigoma.requestid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.util.UUID;

public abstract class MessageHeader {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHeader.class);

    public static final String X_REQUEST_ID_HEADER = "X-Request-Id";
    public static final String REQUEST_ID_LOG = "request_id";

    public void resolvingMDC(Message message) {
        MDC.put(REQUEST_ID_LOG,
                (String) message
                        .getMessageProperties()
                        .getHeaders()
                        .getOrDefault(X_REQUEST_ID_HEADER, UUID.randomUUID().toString()));

        LOGGER.info("[MESSAGE RESOLVING] requestId {}", MDC.get(REQUEST_ID_LOG));
    }

    public Message createMessage(final String strJson) {
        return createMessage(strJson, MDC.get(REQUEST_ID_LOG));
    }

    public Message createMessage(final String strJson, final String requestId) {
        LOGGER.info("[MESSAGE CREATE] requestId {}", MDC.get(REQUEST_ID_LOG));

        return MessageBuilder
                .withBody(strJson.getBytes())
                .setHeader(X_REQUEST_ID_HEADER, requestId)
                .build();
    }
}
