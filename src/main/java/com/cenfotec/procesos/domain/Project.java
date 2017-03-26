package com.cenfotec.procesos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "technology")
    private String technology;

    @Column(name = "device")
    private String device;

    @ManyToOne(optional = false)
    @NotNull
    private Company company;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private Set<Risk> risks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Project description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public Project technology(String technology) {
        this.technology = technology;
        return this;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getDevice() {
        return device;
    }

    public Project device(String device) {
        this.device = device;
        return this;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Company getCompany() {
        return company;
    }

    public Project company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Project tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Project addTask(Task task) {
        this.tasks.add(task);
        task.setProject(this);
        return this;
    }

    public Project removeTask(Task task) {
        this.tasks.remove(task);
        task.setProject(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Risk> getRisks() {
        return risks;
    }

    public Project risks(Set<Risk> risks) {
        this.risks = risks;
        return this;
    }

    public Project addRisk(Risk risk) {
        this.risks.add(risk);
        risk.setProject(this);
        return this;
    }

    public Project removeRisk(Risk risk) {
        this.risks.remove(risk);
        risk.setProject(null);
        return this;
    }

    public void setRisks(Set<Risk> risks) {
        this.risks = risks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        if (project.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", technology='" + technology + "'" +
            ", device='" + device + "'" +
            '}';
    }
}
