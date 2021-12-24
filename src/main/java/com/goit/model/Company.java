package com.goit.model;

import com.goit.dao.IObjectToString;
import com.google.gson.annotations.SerializedName;
import javax.persistence.*;

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