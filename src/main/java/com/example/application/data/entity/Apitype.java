package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Apitype extends AbstractEntity {
    private String apitypename;

    public Apitype() { }

    public Apitype(String apitypename) {
        this.apitypename = apitypename;
    }

    public String getApitypename() {
        return apitypename;
    }

    public void setApitypename(String apitypename) {
        this.apitypename = apitypename;
    }
}