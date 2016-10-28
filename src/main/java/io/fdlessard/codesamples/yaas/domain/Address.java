package io.fdlessard.codesamples.yaas.domain;

/**
 * Created by fdlessard on 16-10-27.
 */

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Address {


    /**
     * (Required)
     */
    @JsonProperty("id")
    private String id;
    /**
     * (Required)
     */
    @JsonProperty("contactName")
    private String contactName;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("street")
    private String street;
    @JsonProperty("streetNumber")
    private String streetNumber;
    @JsonProperty("streetAppendix")
    private String streetAppendix;
    @JsonProperty("extraLine1")
    private String extraLine1;
    @JsonProperty("extraLine2")
    private String extraLine2;
    @JsonProperty("extraLine3")
    private String extraLine3;
    @JsonProperty("extraLine4")
    private String extraLine4;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("state")
    private String state;
    @JsonProperty("contactPhone")
    private String contactPhone;
    /**
     * Values like: Billing / Shipping or any other custom tag
     */
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    /**
     * (Required)
     */
    @JsonProperty("isDefault")
    private Boolean isDefault;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
     * (Required)
     *
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * (Required)
     *
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * (Required)
     *
     * @return The contactName
     */
    @JsonProperty("contactName")
    public String getContactName() {
        return contactName;
    }

    /**
     * (Required)
     *
     * @param contactName The contactName
     */
    @JsonProperty("contactName")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return The companyName
     */
    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName The companyName
     */
    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return The street
     */
    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    /**
     * @param street The street
     */
    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return The streetNumber
     */
    @JsonProperty("streetNumber")
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber The streetNumber
     */
    @JsonProperty("streetNumber")
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @return The streetAppendix
     */
    @JsonProperty("streetAppendix")
    public String getStreetAppendix() {
        return streetAppendix;
    }

    /**
     * @param streetAppendix The streetAppendix
     */
    @JsonProperty("streetAppendix")
    public void setStreetAppendix(String streetAppendix) {
        this.streetAppendix = streetAppendix;
    }

    /**
     * @return The extraLine1
     */
    @JsonProperty("extraLine1")
    public String getExtraLine1() {
        return extraLine1;
    }

    /**
     * @param extraLine1 The extraLine1
     */
    @JsonProperty("extraLine1")
    public void setExtraLine1(String extraLine1) {
        this.extraLine1 = extraLine1;
    }

    /**
     * @return The extraLine2
     */
    @JsonProperty("extraLine2")
    public String getExtraLine2() {
        return extraLine2;
    }

    /**
     * @param extraLine2 The extraLine2
     */
    @JsonProperty("extraLine2")
    public void setExtraLine2(String extraLine2) {
        this.extraLine2 = extraLine2;
    }

    /**
     * @return The extraLine3
     */
    @JsonProperty("extraLine3")
    public String getExtraLine3() {
        return extraLine3;
    }

    /**
     * @param extraLine3 The extraLine3
     */
    @JsonProperty("extraLine3")
    public void setExtraLine3(String extraLine3) {
        this.extraLine3 = extraLine3;
    }

    /**
     * @return The extraLine4
     */
    @JsonProperty("extraLine4")
    public String getExtraLine4() {
        return extraLine4;
    }

    /**
     * @param extraLine4 The extraLine4
     */
    @JsonProperty("extraLine4")
    public void setExtraLine4(String extraLine4) {
        this.extraLine4 = extraLine4;
    }

    /**
     * @return The zipCode
     */
    @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode The zipCode
     */
    @JsonProperty("zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The contactPhone
     */
    @JsonProperty("contactPhone")
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone The contactPhone
     */
    @JsonProperty("contactPhone")
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Values like: Billing / Shipping or any other custom tag
     *
     * @return The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * Values like: Billing / Shipping or any other custom tag
     *
     * @param tags The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * (Required)
     *
     * @return The isDefault
     */
    @JsonProperty("isDefault")
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * (Required)
     *
     * @param isDefault The isDefault
     */
    @JsonProperty("isDefault")
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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

