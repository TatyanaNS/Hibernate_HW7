package com.goit.service;

import com.goit.dao.DeveloperDao;
import com.goit.model.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;

import javax.persistence.TypedQuery;

public class DeveloperService {

  private static final Logger LOGGER = LogManager.getLogger(DeveloperService.class);
  private static final DeveloperDao developerDao = DeveloperDao.getInstance();
  CompanyService companyDao = CompanyService.getInstance();
  private static DeveloperService instance;

  private DeveloperService() {
  }

  public static DeveloperService getInstance() {
    if (instance == null) {
      instance = new DeveloperService();
    }
    return instance;
  }

  public List<Developer> getAll() {
    companyDao.getAll();
    return developerDao.getAll();
  }

  public Optional<Developer> get(long id) {
    return developerDao.get(id);
  }

  public void update(Developer developer) {
    developerDao.update(developer);
  }

  public void create(Developer developer) {
    developerDao.create(developer);
  }

  public void delete(Developer developer) {
    developerDao.delete(developer);
  }

  public List<Developer> developersOfIndustry(String industry) {
    return developerDao.developersOfIndustry(industry);
  }

  public List<Developer> developersOfLevel(String level) {
    return developerDao.developersOfLevel(level);
  }
}