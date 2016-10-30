package io.fdlessard.codesamples.yaas.service.interceptor;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by fdlessard on 16-10-26.
 */

public class BasicAuthorizationRequestInterceptor implements ClientHttpRequestInterceptor {

    private final static String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    private String basicAuthUsername;

    private String basicAuthPassword;

    private String authorizationString;

    private String authorizationHeaderString;

    public BasicAuthorizationRequestInterceptor(String basicAuthUsername, String basicAuthPassword ) {

        this.basicAuthUsername = basicAuthUsername;
        this.basicAuthPassword = basicAuthPassword;

        String authorizationString =   String.join(":", basicAuthUsername, basicAuthPassword);
        byte[] encodedAuthorization = Base64.encodeBase64(authorizationString.getBytes(Charset.forName("US-ASCII")) );
        authorizationHeaderString = AUTHORIZATION_HEADER_PREFIX + new String( encodedAuthorization );
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.set( "AUTHORIZATION_HEADER_KEY", authorizationHeaderString );

        return clientHttpRequestExecution.execute(request, body);
    }
}
