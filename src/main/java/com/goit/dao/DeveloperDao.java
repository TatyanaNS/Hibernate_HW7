package com.goit.dao;

import com.goit.model.*;
import org.apache.logging.log4j.*;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

public class DeveloperDao extends AbstractDao<Developer> {

  private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

  private static DeveloperDao instance;

  private DeveloperDao() {
  }

  public static DeveloperDao getInstance() {
    if (instance == null) {
      instance = new DeveloperDao();
    }
    return instance;
  }

  @Override
  public void delete(Developer entity) {
    entity = em.merge(entity);
    em.getTransaction().begin();
    for (Project project : entity.getProjects()) {
      project.getDevelopers().remove(entity);
    }
    for (Skill skill : entity.getSkills()) {
      skill.getDevelopers().remove(entity);
    }
    em.remove(entity);
    em.getTransaction().commit();
  }

  public List<Developer> developersOfIndustry(String industry) {
    TypedQuery<Developer> developersOfIndustry = em.createNamedQuery("getDevelopersOfIndustry", Developer.class);
    developersOfIndustry.setParameter("industry", industry);
    return developersOfIndustry.getResultStream().collect(Collectors.toList());
  }

  public List<Developer> developersOfLevel(String level) {
    TypedQuery<Developer> developersOfLevel = em.createNamedQuery("getDevelopersOfLevel", Developer.class);
    developersOfLevel.setParameter("level", level);
    return developersOfLevel.getResultStream().collect(Collectors.toList());
  }
}