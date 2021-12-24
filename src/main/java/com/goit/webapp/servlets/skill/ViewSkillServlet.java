package com.goit.webapp.servlets.skill;

import com.goit.model.Skill;
import com.goit.service.*;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;

@WebServlet("/skills/*")
public class ViewSkillServlet extends HttpServlet {

  public static final Logger LOGGER = LogManager.getLogger(ViewSkillServlet.class);
  private SkillService service;

  @Override
  public void init() {
    this.service = (SkillService) getServletContext().getAttribute("skillService");
  }

  @Override
  protected void doGet(
      HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    String id = requestURI.replace("/skills/", "");
    if ("new".equalsIgnoreCase(id)) {
      req.setAttribute("skill", new Skill());
      req.setAttribute("isNew", true);
      req.getRequestDispatcher("/jsp/skill.jsp").forward(req, resp);
    }
    Optional<Skill> skillOpt = service.get(Long.parseLong(id));
    if (skillOpt.isPresent()) {
      Skill skill = skillOpt.get();
      req.setAttribute("skill", skill);
      req.getRequestDispatcher("/jsp/skill.jsp").forward(req, resp);
    }
    resp.sendRedirect("/skills");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HandleBodyUtil.getModelFromStream(req.getInputStream(), Skill.class)
        .ifPresent(skill -> {
          service.update(skill);
        });
    resp.sendRedirect("/skills");
  }
}