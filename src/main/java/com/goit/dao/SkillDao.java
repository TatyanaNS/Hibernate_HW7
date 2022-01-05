package com.goit.dao;

import java.util.*;
import java.util.stream.Collectors;

import com.goit.model.*;
import org.apache.logging.log4j.*;

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

  @Override
  public void delete(Skill entity) {
    entity = em.merge(entity);
    em.getTransaction().begin();
    for (Developer developer : entity.getDevelopers()) {
      if (!developer.getSkills().isEmpty()) {
        developer.getSkills().remove(entity);
      }
    }
    em.remove(entity);
    em.getTransaction().commit();
  }
}