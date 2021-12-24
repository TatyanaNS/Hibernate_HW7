package com.goit.webapp.servlets;

import com.goit.model.Project;
import com.goit.service.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/project_developers")
public class ProjectDeveloperServlet extends HttpServlet {

  private ProjectService service;
  private List<Project> projectDeveloper = new ArrayList<>();

  @Override
  public void init() {
    this.service = (ProjectService) getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("listProject", service.getAll());
    req.setCharacterEncoding("UTF-8");
    req.setAttribute("listProjectDevelopers", projectDeveloper);
        req.getRequestDispatcher("/jsp/project_developers.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Project> modelFromStream = HandleBodyUtil.
        getModelFromStream(req.getInputStream(), Project.class);
    modelFromStream.ifPresent(project -> {
      projectDeveloper = service.getProjectDevelopers(project);
    });
    resp.sendRedirect("/project_developers");
  }
}