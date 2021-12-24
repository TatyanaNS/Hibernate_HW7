package com.goit.model;

import com.goit.dao.IObjectToString;
import com.google.gson.annotations.SerializedName;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project implements IObjectToString {

  @Id
  @GeneratedValue(generator = "projects_id_seq")
  @SerializedName("id")
  private Long id;
  @Column(name = "project_name")
  @SerializedName("name")
  private String name;

  @Temporal(TemporalType.DATE)
  @Column(name = "created")
  @SerializedName("created")
  private Date created;
  @Column(name = "cost")
  @SerializedName("cost")
  private Double cost;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "developer_project",
      joinColumns = { @JoinColumn(name = "project_id") },
      inverseJoinColumns = { @JoinColumn(name = "developer_id") }
  )
  private List<Developer> developers = new ArrayList<>();

  public List<Developer> getDevelopers() {
    return developers;
  }

  public void setDevelopers(List<Developer> developers) {
    this.developers = developers;
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

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}