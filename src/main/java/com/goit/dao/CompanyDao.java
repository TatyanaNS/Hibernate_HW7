package com.goit.dao;

import com.goit.model.Company;
import com.goit.model.Customer;
import com.goit.model.Developer;
import com.goit.model.Project;
import org.apache.logging.log4j.*;

public class CompanyDao extends AbstractDao<Company> {

  private static final Logger LOGGER = LogManager.getLogger(CompanyDao.class);
  private static CompanyDao instance;

  private CompanyDao() {
    super(Company.class);
  }

  public static CompanyDao getInstance() {
    if (instance == null) {
      instance = new CompanyDao();
    }
    return instance;
  }

  @Override
  public void delete(Company entity) {
    entity = em.merge(entity);
    em.getTransaction().begin();
    for (Project project : entity.getProjects()) {
      if (!project.getCompanies().isEmpty()) {
        project.getCompanies().remove(entity);
      }
    }
    for (Developer developer : entity.getDevelopers()) {
      developer.getCompany().getDevelopers().remove(entity);
    }
    em.remove(entity);
    em.getTransaction().commit();
  }
}