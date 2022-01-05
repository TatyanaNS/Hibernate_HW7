package com.goit.model;

import com.goit.dao.IObjectToString;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "projects")
@NamedQueries({
        @NamedQuery(name = "getAllProjects", query = "from Project"),
        @NamedQuery(name = "developersByProjectId", query = "select p from Project p where p.id = :id")
})
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

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL, CascadeType.PERSIST, CascadeType.REFRESH},
          fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
          name = "developer_project",
          joinColumns = { @JoinColumn(name = "project_id") },
          inverseJoinColumns = { @JoinColumn(name = "developer_id") }
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Developer> developers;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL, CascadeType.PERSIST, CascadeType.REFRESH},
          fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
          name = "customer_project",
          joinColumns = { @JoinColumn(name = "project_id") },
          inverseJoinColumns = { @JoinColumn(name = "customer_id") }
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private transient List<Customer> customers;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL, CascadeType.PERSIST, CascadeType.REFRESH},
          fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
          name = "company_project",
          joinColumns = { @JoinColumn(name = "project_id") },
          inverseJoinColumns = { @JoinColumn(name = "company_id") }
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Company> companies;

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

  public List<Developer> getDevelopers() {
    return developers;
  }

  public void setDevelopers(List<Developer> developers) {
    this.developers = developers;
  }

  public List<Company> getCompanies() {
    return companies;
  }

  public void setCompanies(List<Company> companies) {
    this.companies = companies;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}