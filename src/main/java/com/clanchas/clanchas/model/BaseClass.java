package com.clanchas.clanchas.model;

public class BaseClass {
    /**
     * campo id
     */
    protected Long id;

    public BaseClass(Long id) {
        this.id = id;
    }

    public BaseClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
