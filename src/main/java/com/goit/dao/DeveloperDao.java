package com.goit.dao;

import org.apache.logging.log4j.*;
import com.goit.model.Developer;

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
}