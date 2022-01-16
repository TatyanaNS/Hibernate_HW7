package com.goit.model;

import com.goit.dao.IObjectToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;
import javax.persistence.*;


@Entity
@Table(name = "developers")
@NamedQueries({
        @NamedQuery(name = "getAll", query = "from Developer"),
        @NamedQuery(name = "getDevelopersOfIndustry", query = "select d from Developer d join d.skills s where s.industry = :industry"),
        @NamedQuery(name = "getDevelopersOfLevel", query = "select d from Developer d join d.skills s where s.level = :level")
})
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

  @ManyToOne
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;

  @Column(name = "salary")
  private Double salary;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
      name = "developer_skill",
      joinColumns = { @JoinColumn(name = "developer_id") },
      inverseJoinColumns = { @JoinColumn(name = "skill_id") }
  )
  private List<Skill> skills = new ArrayList<>();

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
          fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
          name = "developer_project",
          joinColumns = { @JoinColumn(name = "developer_id") },
          inverseJoinColumns = { @JoinColumn(name = "project_id") }
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private transient List<Project> projects = new ArrayList<>();

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

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(List<Skill> skills) {
    this.skills = skills;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}