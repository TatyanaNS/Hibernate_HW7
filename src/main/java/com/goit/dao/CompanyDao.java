package com.goit.dao;

import com.goit.model.Company;
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
}