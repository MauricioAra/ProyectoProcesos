package com.cenfotec.procesos.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "cost", nullable = false)
    private Double cost;

    @NotNull
    @Column(name = "time", nullable = false)
    private Double time;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "real_hour")
    private Double realHour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Task name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public Task cost(Double cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getTime() {
        return time;
    }

    public Task time(Double time) {
        this.time = time;
        return this;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Boolean isStatus() {
        return status;
    }

    public Task status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getRealHour() {
        return realHour;
    }

    public Task realHour(Double realHour) {
        this.realHour = realHour;
        return this;
    }

    public void setRealHour(Double realHour) {
        this.realHour = realHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        if (task.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", cost='" + cost + "'" +
            ", time='" + time + "'" +
            ", status='" + status + "'" +
            ", realHour='" + realHour + "'" +
            '}';
    }
}
