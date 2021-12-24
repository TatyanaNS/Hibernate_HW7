package com.goit.webapp.servlets.customer;

import com.goit.model.Customer;
import com.goit.service.*;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;

@WebServlet("/customers/*")
public class ViewCustomerServlet extends HttpServlet {

  public static final Logger LOGGER = LogManager.getLogger(ViewCustomerServlet.class);
  private CustomerService service;

  @Override
  public void init() {
    this.service = (CustomerService) getServletContext().getAttribute("customerService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    String id = requestURI.replace("/customers/", "");
    if ("new".equalsIgnoreCase(id)) {
      req.setAttribute("customer", new Customer());
      req.setAttribute("isNew", true);
      req.getRequestDispatcher("/jsp/customer.jsp").forward(req, resp);
      return;
    }
    Optional<Customer> customerOpt = service.get(Long.parseLong(id));
    if (customerOpt.isPresent()) {
      Customer customer = customerOpt.get();
      req.setAttribute("customer", customer);
      req.getRequestDispatcher("/jsp/customer.jsp").forward(req, resp);
      return;
    }
    resp.sendRedirect("/customers");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HandleBodyUtil.getModelFromStream(req.getInputStream(), Customer.class)
        .ifPresent(customer -> {
          service.update(customer);
        });
    resp.sendRedirect("/customers");
  }
}