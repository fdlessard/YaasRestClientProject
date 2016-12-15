package io.fdlessard.codebites.yaas.gateway.errorhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by fdlessard on 16-10-29.
 */
public class CustomerAccountResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAccountResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

       // HttpStatus statusCode = response.getStatusCode();
        LOGGER.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
/*
        if(statusCode == HttpStatus.NOT_FOUND) {
            LOGGER.error("NOT FOUND");
        } else if (statusCode == HttpStatus.UNAUTHORIZED) {
            LOGGER.error("UNAUTHORIZED");
        }*/
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        HttpStatus.Series series = response.getStatusCode().series();
        return HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series);
    }

}
