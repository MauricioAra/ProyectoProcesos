package com.cenfotec.procesos.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Risk entity.
 */
public class RiskDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String tag;

    private String probability;

    private String impact;

    private Boolean status;

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
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RiskDTO riskDTO = (RiskDTO) o;

        if ( ! Objects.equals(id, riskDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RiskDTO{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", tag='" + tag + "'" +
            ", probability='" + probability + "'" +
            ", impact='" + impact + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
