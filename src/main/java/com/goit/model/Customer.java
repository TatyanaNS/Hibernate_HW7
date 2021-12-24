package com.goit.model;

import com.goit.dao.IObjectToString;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer implements IObjectToString {

  @Id
  @GeneratedValue(generator = "customers_id_seq")
  private Long id;
  @Column(name = "customer_name")
  private String name;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "customer_project",
      joinColumns = { @JoinColumn(name = "customer_id") },
      inverseJoinColumns = { @JoinColumn(name = "project_id") }
  )
  private List<Project> projects = new ArrayList<>();

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

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

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}