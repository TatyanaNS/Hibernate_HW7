package com.goit.model;

import com.goit.dao.IObjectToString;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "companies")
public class Company implements IObjectToString {

  @Id
  @GeneratedValue(generator = "companies_id_seq")
  @SerializedName("id")
  private Long id;
  @Column(name = "company_name")
  @SerializedName("name")
  private String name;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
          fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
          name = "company_project",
          joinColumns = { @JoinColumn(name = "company_id") },
          inverseJoinColumns = { @JoinColumn(name = "project_id") }
  )
  private transient List<Project> projects = new ArrayList<>();

  @OneToMany(mappedBy = "company")
  private transient List<Developer> developers = new ArrayList<>();

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

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public List<Developer> getDevelopers() {
    return developers;
  }

  public void setDevelopers(List<Developer> developers) {
    this.developers = developers;
  }

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}