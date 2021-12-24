package com.goit.webapp.servlets.company;

import com.goit.model.Company;
import com.goit.service.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;

@WebServlet("/companies/*")
public class ViewCompanyServlet extends HttpServlet {

  public static final Logger LOGGER = LogManager.getLogger(ViewCompanyServlet.class);
  private CompanyService service;
  protected Gson jsonParser = new Gson();

  @Override
  public void init() throws ServletException {
    this.service = (CompanyService) getServletContext().getAttribute("companyService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    String id = requestURI.replace("/companies/", "");
    if ("new".equalsIgnoreCase(id)) {
      req.setAttribute("company", new Company());
      req.setAttribute("isNew", true);
      req.getRequestDispatcher("/jsp/company.jsp").forward(req, resp);
    }
    Optional<Company> companyOpt = service.get(Long.parseLong(id));
    if (companyOpt.isPresent()) {
      Company company = companyOpt.get();
      req.setAttribute("company", company);
      req.getRequestDispatcher("/jsp/company.jsp").forward(req, resp);
    }
    resp.sendRedirect("/companies");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HandleBodyUtil.getModelFromStream(req.getInputStream(), Company.class)
        .ifPresent(company -> {
          service.update(company);
        });
    resp.sendRedirect("/companies");
  }
}