package com.goit.service;

import com.goit.dao.ProjectDao;
import com.goit.model.Project;
import java.text.ParseException;
import java.util.*;
import org.apache.logging.log4j.*;

public class ProjectService {

  private static final Logger LOGGER = LogManager.getLogger(ProjectService.class);
  private static final ProjectDao projectDao = ProjectDao.getInstance();
  private static ProjectService instance;

  private ProjectService() {
  }

  public static ProjectService getInstance() {
    if (instance == null) {
      instance = new ProjectService();
    }
    return instance;
  }

  public List<Project> getAll() {
    return projectDao.getAll();
  }

  public Optional<Project> get(long id) {
    return projectDao.get(id);
  }

  public void update(Project project) {
    projectDao.update(project);
  }

  public void create(Project project) {
    projectDao.create(project);
  }

  public void delete(Project project) {
    projectDao.delete(project);
  }

  public List<Project> getProjectDevelopers(Project project) {
    return projectDao.getProjectDevelopers(project);
  }

  public Map<String, Double> getSumProjectSalary(Long projectId) {
    return projectDao.getSumProjectSalary(projectId);
  }

  public List<String> getProjectInfo() {
    return projectDao.getProjectInfo();
  }
}