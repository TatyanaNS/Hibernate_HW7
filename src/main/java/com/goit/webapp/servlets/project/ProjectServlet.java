package com.goit.webapp.servlets.project;

import com.goit.model.Project;
import com.goit.service.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;

@WebServlet("/projects")
public class  ProjectServlet extends HttpServlet {

  private ProjectService service;
  private static final Logger LOGGER = LogManager.getLogger(ProjectServlet.class);

  @Override
  public void init() {
    this.service = (ProjectService) getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String deleteId = req.getParameter("deleteId");
    if (deleteId != null) {
      Optional<Project> project = service.get(Long.parseLong(deleteId));
      project.ifPresent(p -> service.delete(p));
      resp.sendRedirect("/projects");
    } else {
      List<Project> all = null;
      all = service.getAll();
      req.setAttribute("projects", all);
      req.setCharacterEncoding("UTF-8");
      req.getRequestDispatcher("/jsp/projects.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Project> modelFromStream = HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class);
    modelFromStream.ifPresent(project -> service.create(project));
    System.out.println("Created project with status code:" + resp.getStatus());
    resp.sendRedirect("/projects");

  }
}