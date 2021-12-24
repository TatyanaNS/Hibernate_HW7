package com.goit.dao;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.*;
import com.goit.model.Skill;

public class SkillDao extends AbstractDao<Skill> {

  private static final Logger LOGGER = LogManager.getLogger(SkillDao.class);
  private static SkillDao instance;

  private SkillDao() {
    super(Skill.class);
  }

  public static SkillDao getInstance() {
    if (instance == null) {
      instance = new SkillDao();
    }
    return instance;
  }

  public List<String> getDevelopersOfIndustry(String industry) {
    return getAll().stream()
        .filter(skill -> industry.equals(skill.getIndustry()))
        .flatMap(d -> d.getDevelopers().stream()
            .map(f -> f.getLastName() + " " + f.getFirstName() + " " + f.getSurname()))
        .collect(Collectors.toList());
  }

  public List<String> getDevelopersOfLevel(String level) {
    return getAll().stream()
        .filter(skill -> level.equals(skill.getLevel()))
        .flatMap(d -> d.getDevelopers().stream()
            .map(f -> f.getLastName() + " " + f.getFirstName() + " " + f.getSurname()))
        .collect(Collectors.toList());
  }
}