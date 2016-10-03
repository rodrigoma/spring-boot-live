package br.com.rodrigoma.requestid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class RequestIdHeaderFilter implements Filter {

    // TODO 01 Create Filter

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIdHeaderFilter.class);

    public static final String X_REQUEST_ID_HEADER = "X-Request-Id";
    public static final String REQUEST_ID_LOG = "request_id";

    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        insertIntoMDC(request);
        try {
            chain.doFilter(request, response);
        } finally {
            clearMDC();
        }
    }

    void insertIntoMDC(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String currentReqId = httpServletRequest.getHeader(X_REQUEST_ID_HEADER);

            if (currentReqId == null || currentReqId.trim().equals("")) {
                currentReqId = UUID.randomUUID().toString();
                LOGGER.info("[FILTER] No requestId found in Header. Generated : {}", currentReqId);
            } else {
                LOGGER.info("[FILTER] Found requestId in Header : {}", currentReqId);
            }

            MDC.put(REQUEST_ID_LOG, currentReqId);
        }
    }

    void clearMDC() {
        MDC.remove(REQUEST_ID_LOG);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }
}