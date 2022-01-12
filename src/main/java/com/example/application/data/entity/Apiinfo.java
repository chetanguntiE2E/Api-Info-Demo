package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Apiinfo extends AbstractEntity {

    @NotEmpty
    private String apiName = "";

    @NotNull
    @ManyToOne
    private Apitype apitype;

    @NotEmpty
    private String purpose = "";

    @NotEmpty
    private String request = "";

    @NotEmpty
    private String response= "";

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Apitype getApitype() {
        return apitype;
    }

    public void setApitype(Apitype apitype) {
        this.apitype = apitype;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }




    @Override
    public String toString() {
    return apiName +" "+ purpose + " " + request + " " + response ;
    }

}

