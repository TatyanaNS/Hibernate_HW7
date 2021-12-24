package com.goit.webapp.servlets.customer;

import com.goit.model.Customer;
import com.goit.service.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

  private CustomerService service;

  @Override
  public void init() {
    this.service = (CustomerService) getServletContext().getAttribute("customerService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String deleteId = req.getParameter("deleteId");
    if (deleteId != null) {
      Optional<Customer> customer = service.get(Long.parseLong(deleteId));
      customer.ifPresent(c -> service.delete(c));
      resp.sendRedirect("/customers");
    } else {
      List<Customer> all = null;
      try {
        all = service.getAll();
      } catch (ParseException e) {
        e.printStackTrace();
      }
      req.setAttribute("customers", all);
      req.getRequestDispatcher("/jsp/customers.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Customer> modelFromStream = HandleBodyUtil.getModelFromStream(req.getInputStream(), Customer.class);
    modelFromStream.ifPresent(customer -> service.create(customer));
    resp.sendRedirect("/customers");
  }
}