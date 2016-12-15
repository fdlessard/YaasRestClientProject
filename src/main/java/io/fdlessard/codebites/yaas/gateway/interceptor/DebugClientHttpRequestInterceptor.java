package io.fdlessard.codebites.yaas.gateway.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by fdlessard on 16-11-20.
 */
public class DebugClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugClientHttpRequestInterceptor.class);

    public DebugClientHttpRequestInterceptor() {
    }

    private void debug(String title, HttpHeaders headers) {

        LOGGER.info("============== " + title + " ==============");
        for (String k : headers.keySet()) {
            LOGGER.info(String.format("%s = %s", k, headers.get(k)));
        }
        LOGGER.info("============== " + title + " ==============");

    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        debug("Request: ", request.getHeaders());
        return execution.execute(request, body);
    }

}
