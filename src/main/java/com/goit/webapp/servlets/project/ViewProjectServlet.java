package com.goit.webapp.servlets.project;

import com.goit.model.Project;
import com.goit.service.*;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;

@WebServlet("/projects/*")
public class ViewProjectServlet extends HttpServlet {

  public static final Logger LOGGER = LogManager.getLogger(ViewProjectServlet.class);
  private ProjectService service;

  @Override
  public void init() {
    this.service = (ProjectService) getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    String id = requestURI.replace("/projects/", "");
    if ("new".equalsIgnoreCase(id)) {
      req.setAttribute("project", new Project());
      req.setAttribute("isNew", true);
      req.getRequestDispatcher("/jsp/project.jsp").forward(req, resp);
    }
    Optional<Project> projectOpt = service.get(Long.parseLong(id));
    if (projectOpt.isPresent()) {
      Project project = projectOpt.get();
      req.setAttribute("project", project);
      req.getRequestDispatcher("/jsp/project.jsp").forward(req, resp);
    }
    resp.sendRedirect("/projects");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Project> projectOpt = HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class);
        projectOpt.ifPresent(project -> {
          service.update(project);
        });
    resp.sendRedirect("/projects");
  }

  }