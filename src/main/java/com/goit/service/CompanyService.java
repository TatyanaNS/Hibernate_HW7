package com.goit.service;

import com.goit.dao.CompanyDao;
import com.goit.model.Company;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.*;

public class CompanyService {

  private static CompanyService instance;
  private static final Logger LOGGER = LogManager.getLogger(CompanyService.class);
  private static final CompanyDao companyDao = CompanyDao.getInstance();

  private CompanyService() {
  }

  public static CompanyService getInstance() {
    if (instance == null) {
      instance = new CompanyService();
    }
    return instance;
  }

  public List<Company> getAll() {
    return companyDao.getAll();
  }

  public Optional<Company> get(long id) {
    return companyDao.get(id);
  }

  public void update(Company company) {
    companyDao.update(company);
  }

  public void create(Company company) {
    companyDao.create(company);
  }

  public void delete(Company company) {
    companyDao.delete(company);
  }
}