package com.goit.webapp.servlets;

import com.goit.dao.ProjectDao;
import com.goit.model.Project;
import com.goit.service.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/project_salaries")
public class SumProjectSalaryServlet extends HttpServlet {

  private ProjectService service;
  private Map<String, Double> salary = new HashMap<>();

  @Override
  public void init() {
    this.service = (ProjectService) getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("listProject", service.getAll());
    req.setCharacterEncoding("UTF-8");
    req.setAttribute("salary", salary);
    req.getRequestDispatcher("/jsp/project_salaries.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Project> modelFromStream = HandleBodyUtil.
        getModelFromStream(req.getInputStream(), Project.class);
//    System.out.println("get salarySum = " + modelFromStream);
    modelFromStream.ifPresent(project -> {
      salary = service.getSumProjectSalary(project.getId());
//      System.out.println("get SumProjectSalaries = " + service.getSumProjectSalary(project.getId()));
    });
//    System.out.println("Get SumProjectSalaries with status code:" + resp.getStatus());
    resp.sendRedirect("/project_salaries");
  }
}