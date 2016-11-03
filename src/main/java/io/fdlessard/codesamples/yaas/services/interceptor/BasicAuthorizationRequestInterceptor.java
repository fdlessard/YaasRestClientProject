package io.fdlessard.codesamples.yaas.services.interceptor;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by fdlessard on 16-10-26.
 */

public class BasicAuthorizationRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String CHARSET_NAME = "US-ASCII";

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthorizationRequestInterceptor.class);

    private String authorizationHeaderString;

    public BasicAuthorizationRequestInterceptor(String basicAuthUsername, String basicAuthPassword ) {

        String authorizationString =   String.join(":", basicAuthUsername, basicAuthPassword);
        byte[] encodedAuthorization = Base64.encodeBase64(authorizationString.getBytes(Charset.forName(CHARSET_NAME)) );
        authorizationHeaderString = AUTHORIZATION_HEADER_PREFIX + new String( encodedAuthorization );
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.set( AUTHORIZATION_HEADER_KEY, authorizationHeaderString );
        // TODO ... put **** on the first few characters of passwords
        LOGGER.debug("Added Authorization header {}", authorizationHeaderString);

        return clientHttpRequestExecution.execute(request, body);
    }
}
