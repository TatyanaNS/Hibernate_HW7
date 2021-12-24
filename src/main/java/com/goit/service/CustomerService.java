package com.goit.service;

import com.goit.dao.CustomerDao;
import com.goit.model.Customer;
import java.text.ParseException;
import java.util.*;
import org.apache.logging.log4j.*;

public class CustomerService {

  private static CustomerService instance;
  private static final Logger LOGGER = LogManager.getLogger(CompanyService.class);
  private static final CustomerDao customerDao = CustomerDao.getInstance();

  private CustomerService() {
  }

  public static CustomerService getInstance() {
    if (instance == null) {
      instance = new CustomerService();
    }
    return instance;
  }

  public List<Customer> getAll() throws ParseException {
    return customerDao.getAll();
  }

  public Optional<Customer> get(long id) {
    return customerDao.get(id);
  }

  public void update(Customer customer) {
    customerDao.update(customer);
  }

  public void create(Customer customer) {
    customerDao.create(customer);
  }

  public void delete(Customer customer) {
    customerDao.delete(customer);
  }
}