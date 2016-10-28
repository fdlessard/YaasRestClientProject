package io.fdlessard.codesamples.yaas.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

import static org.apache.commons.lang3.math.NumberUtils.toInt;

/**
 * Created by fdlessard on 16-10-26.
 */
public class YaasRequestInterceptor  implements ClientHttpRequestInterceptor {


    public static final String HEADER_REQUEST_ID = "hybris-request-id";
    public static final String HEADER_HOP = "hybris-hop";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        String hybrisRequestId = headers.getFirst(HEADER_REQUEST_ID);
        if(StringUtils.isEmpty(hybrisRequestId)) {
            headers.set(HEADER_REQUEST_ID , "");
        }
        String hybrisHop = headers.getFirst(HEADER_HOP);
        String newHybrisHop = "0";
        if (NumberUtils.isDigits(hybrisHop)) {
            int hop = NumberUtils.toInt(hybrisHop);
            hop++;
            newHybrisHop = Integer.toString(hop);
        }
        headers.set(HEADER_HOP, newHybrisHop);

        return clientHttpRequestExecution.execute(request, body);

    }
}
