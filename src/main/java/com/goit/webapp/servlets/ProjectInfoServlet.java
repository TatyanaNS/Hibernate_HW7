package com.goit.webapp.servlets;

import com.goit.service.ProjectService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/project_info")
public class ProjectInfoServlet extends HttpServlet {

  private ProjectService service;

  @Override
  public void init() {
    this.service = (ProjectService) getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("listProjectInfo", service.getProjectInfo());
    req.setCharacterEncoding("UTF-8");
    req.getRequestDispatcher("/jsp/project_info.jsp").forward(req, resp);
  }
}