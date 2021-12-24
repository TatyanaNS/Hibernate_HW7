package com.goit.webapp.servlets.skill;

import com.goit.model.Skill;
import com.goit.service.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/skills")
public class SkillServlet extends HttpServlet {

  private SkillService service;

  @Override
  public void init() {
    this.service = (SkillService) getServletContext().getAttribute("skillService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String deleteId = req.getParameter("deleteId");
    if (deleteId != null) {
      Optional<Skill> skill = service.get(Long.parseLong(deleteId));
      skill.ifPresent(sk -> service.delete(sk));
      resp.sendRedirect("/skills");
    } else {
      List<Skill> all = service.getAll();
      req.setAttribute("skills", all);
      req.getRequestDispatcher("/jsp/skills.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Optional<Skill> modelFromStream = HandleBodyUtil.getModelFromStream(req.getInputStream(), Skill.class);
    modelFromStream.ifPresent(skill -> service.create(skill));
    resp.sendRedirect("/skills");
  }
}