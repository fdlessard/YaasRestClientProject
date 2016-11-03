package io.fdlessard.codesamples.yaas.services.interceptor;

import com.sap.cloud.yaas.servicesdk.patternsupport.traits.YaasAwareTrait;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by fdlessard on 16-10-26.
 */
public class YaasRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(YaasRequestInterceptor.class);

    public static final String CHECKING_IF_IS_PRESENT = "Checking if {} is present : {}";
    public static final String ADDING_UPDATING_WITH = "Adding/Updating {} with {}";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        String hybrisRequestId = headers.getFirst(YaasAwareTrait.Headers.REQUEST_ID);
        LOGGER.debug(CHECKING_IF_IS_PRESENT, YaasAwareTrait.Headers.REQUEST_ID, hybrisRequestId);
        if (StringUtils.isEmpty(hybrisRequestId)) {
            headers.set(YaasAwareTrait.Headers.REQUEST_ID, StringUtils.EMPTY);
            LOGGER.debug(ADDING_UPDATING_WITH, YaasAwareTrait.Headers.REQUEST_ID, StringUtils.EMPTY);
        }

        String hybrisHop = headers.getFirst(YaasAwareTrait.Headers.HOP);
        LOGGER.debug(CHECKING_IF_IS_PRESENT, YaasAwareTrait.Headers.HOP, hybrisHop);
        String newHybrisHop = "0";
        if (NumberUtils.isDigits(hybrisHop)) {
            int hop = NumberUtils.toInt(hybrisHop);
            hop++;
            newHybrisHop = Integer.toString(hop);
        }
        headers.set(YaasAwareTrait.Headers.HOP, newHybrisHop);
        LOGGER.debug(ADDING_UPDATING_WITH, YaasAwareTrait.Headers.HOP, newHybrisHop);

        return clientHttpRequestExecution.execute(request, body);
    }
}
