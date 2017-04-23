package com.cenfotec.procesos.service.dto;

import java.io.Serializable;

/**
 * Created by mauricioaraica on 4/11/17.
 */
public class TagDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String probability;
    private String impact;
    private String tag;

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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
