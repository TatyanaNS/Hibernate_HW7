package com.goit.dao;

import com.goit.model.Customer;
import org.apache.logging.log4j.*;

public class CustomerDao extends AbstractDao<Customer> {

  private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);
  private static CustomerDao instance;

  private CustomerDao() {
  }

  public static CustomerDao getInstance() {
    if (instance == null) {
      instance = new CustomerDao();
    }
    return instance;
  }
}