package com.cenfotec.procesos.service.dto;

import java.io.Serializable;

/**
 * Created by mauricioaraica on 4/11/17.
 */
public class TagDTO implements Serializable {

    private Long id;
    private String name;

    public TagDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
