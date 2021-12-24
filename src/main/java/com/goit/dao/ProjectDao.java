package com.goit.dao;

import com.goit.model.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.*;

public class ProjectDao extends AbstractDao<Project> {

  private static final Logger LOGGER = LogManager.getLogger(ProjectDao.class);
  private static ProjectDao instance;

  private ProjectDao() {
  }

  public static ProjectDao getInstance() {
    if (instance == null) {
      instance = new ProjectDao();
    }
    return instance;
  }

  public List<Project> getProjectDevelopers(Project project) {
    return getAll().stream()
        .filter(p -> project.getId().equals(p.getId())).collect(Collectors.toList());
  }

  public Map<String, Double> getSumProjectSalary(Long projectId) {
    Map<String, Double> salary = new HashMap<>();
    getAll().stream()
        .filter(project -> projectId.equals(project.getId()))
        .map(project -> salary.put(project.getName(), project.getDevelopers().stream()
            .mapToDouble(Developer::getSalary).sum())).collect(Collectors.toList());
    return salary;
  }

  public List<String> getProjectInfo() {
    return getAll().stream()
        .map(project -> project.getCreated() + " - " + project.getName() + " - " + project.getDevelopers()
            .size()).distinct().collect(
            Collectors.toList());
  }
}