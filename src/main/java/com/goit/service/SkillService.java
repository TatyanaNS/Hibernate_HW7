package com.goit.service;

import com.goit.dao.SkillDao;
import com.goit.model.Skill;
import java.text.ParseException;
import java.util.*;
import org.apache.logging.log4j.*;

public class SkillService {

  private static SkillService instance;
  private static final Logger LOGGER = LogManager.getLogger(CompanyService.class);
  private static final SkillDao skillDao = SkillDao.getInstance();

  private SkillService() {
  }

  public static SkillService getInstance() {
    if (instance == null) {
      instance = new SkillService();
    }
    return instance;
  }

  public List<Skill> getAll() {
    return skillDao.getAll();
  }

  public Optional<Skill> get(long id) {
    return skillDao.get(id);
  }

  public void update(Skill skill) {
    skillDao.update(skill);
  }

  public void create(Skill skill) {
    skillDao.create(skill);
  }

  public void delete(Skill skill) {
    skillDao.delete(skill);
  }

  public List<String> getDevelopersOfIndustry(String industry) {
    return skillDao.getDevelopersOfIndustry(industry);
  }

  public List<String> getDevelopersOfLevel(String level) {
    return skillDao.getDevelopersOfLevel(level);
  }
}