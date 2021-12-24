package com.goit.model;

import com.goit.dao.IObjectToString;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "developers")
public class Developer implements IObjectToString {

  @Id
  @GeneratedValue(generator = "developers_id_seq")
  private Long id;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "surname")
  private String surname;
  @Column(name = "developer_age")
  private Integer developerAge;

  @Temporal(TemporalType.DATE)
  @Column(name = "date_of_birth")
  private Date dateOfBirth;

  @Column(name = "gender")
  private String gender;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @Column(name = "salary")
  private Double salary;

//  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//      fetch = FetchType.EAGER)
//  @JoinTable(
//      name = "developer_skill",
//      joinColumns = { @JoinColumn(name = "developer_id") },
//      inverseJoinColumns = { @JoinColumn(name = "skill_id") }
//  )

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Integer getDeveloperAge() {
    return developerAge;
  }

  public void setDeveloperAge(Integer developerAge) {
    this.developerAge = developerAge;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}