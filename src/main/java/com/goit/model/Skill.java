package com.goit.model;

import com.goit.dao.IObjectToString;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill implements IObjectToString {

  @Id
  @GeneratedValue(generator = "skills_id_seq")
  private Long id;
  private String industry;
  @Column(name = "level_skills")
  private String level;

  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "developer_skill",
      joinColumns = { @JoinColumn(name = "skill_id") },
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

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return jsonObjectString().toJson(this);
  }
}