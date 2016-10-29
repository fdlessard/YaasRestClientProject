package io.fdlessard.codesamples.yaas.domain;

/**
 * Created by fdlessard on 16-10-27.
 */


import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "providerId",
        "id"
})
public class CustomerAccount {

    @JsonProperty("providerId")
    private String providerId;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The providerId
     */
    @JsonProperty("providerId")
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId The providerId
     */
    @JsonProperty("providerId")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}




