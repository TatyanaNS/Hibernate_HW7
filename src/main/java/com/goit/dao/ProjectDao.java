package com.goit.dao;

import com.goit.model.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.Tuple;
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

  @Override
  public void delete(Project entity) {
    entity = em.merge(entity);
    em.getTransaction().begin();
    for (Developer developer : entity.getDevelopers()) {
      if (!developer.getProjects().isEmpty()) {
        developer.getProjects().remove(entity);
      }
    }
//    for (Customer customer : entity.getCustomers()) {
//      if (!customer.getProjects().isEmpty()) {
//        customer.getProjects().remove(entity);
//      }
//    }
    for (Company company : entity.getCompanies()) {
      if (!company.getProjects().isEmpty()) {
        company.getProjects().remove(entity);
      }
    }
    em.remove(entity);
    em.getTransaction().commit();
  }

  @Override
  public List<Project> getAll() {
    TypedQuery<Project> query = em.createQuery("from Project", Project.class);
    System.out.println("Project getAll = " + query.getResultList());
    return query.getResultList();
  }

  public List<Project> getProjectDevelopers(Long id) {
    TypedQuery<Project> projectDeveloper = em.createNamedQuery("developersByProjectId", Project.class);
    projectDeveloper.setParameter("id", id);
    return projectDeveloper.getResultList();
  }

  public Map<Long, String> getProjectInfo() {
    Map<Long, String> projectInfo = em.createQuery("select p.id as p_id," +
            " p.created || ' - ' || p.name || ' - ' || count(distinct d.id) as project_info" +
            " from Project p join p.developers d " +
                    "GROUP BY p.id", Tuple.class).getResultList()
            .stream()
            .collect(
                    Collectors.toMap(
                            tuple -> ((Long) tuple.get("p_id")),
                            tuple -> (String) tuple.get("project_info")
                    )
            );
    return projectInfo;
  }

  public Map<String, Double> getSumProjectSalary(String projectName) {
    Map<String, Double> projectDeveloper = em.createQuery("select p.name as project_name, sum(d.salary) as salary_sum" +
                    " from Project p join p.developers d  GROUP BY p.name", Tuple.class)
            .getResultList()
            .stream()
            .collect(
                    Collectors.toMap(
                            tuple -> ((String) tuple.get("project_name")),
                            tuple -> (Double) tuple.get("salary_sum")
                    )
            );
    Map<String, Double> sumProject = projectDeveloper.entrySet().stream()
            .filter(m -> m.getKey().equals(projectName))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    return sumProject;
  }
}