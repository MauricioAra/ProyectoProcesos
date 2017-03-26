package com.cenfotec.procesos.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Risk.
 */
@Entity
@Table(name = "risk")
public class Risk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tag")
    private String tag;

    @Column(name = "probability")
    private String probability;

    @Column(name = "impact")
    private String impact;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(optional = false)
    @NotNull
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Risk name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public Risk tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProbability() {
        return probability;
    }

    public Risk probability(String probability) {
        this.probability = probability;
        return this;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getImpact() {
        return impact;
    }

    public Risk impact(String impact) {
        this.impact = impact;
        return this;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public Boolean isStatus() {
        return status;
    }

    public Risk status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public Risk project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Risk risk = (Risk) o;
        if (risk.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, risk.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Risk{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", tag='" + tag + "'" +
            ", probability='" + probability + "'" +
            ", impact='" + impact + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
