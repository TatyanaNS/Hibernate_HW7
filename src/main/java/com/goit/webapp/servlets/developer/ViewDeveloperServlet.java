package com.goit.webapp.servlets.developer;

import com.goit.model.Developer;
import com.goit.service.CompanyService;
import com.goit.service.DeveloperService;
import java.util.Optional;
import org.apache.logging.log4j.*;
import com.goit.service.HandleBodyUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/developers/*")
public class ViewDeveloperServlet extends HttpServlet {

    public static final Logger LOGGER = LogManager.getLogger(ViewDeveloperServlet.class);
    private DeveloperService service;

    @Override
    public void init() {
        this.service = (DeveloperService) getServletContext().getAttribute("developerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String id = requestURI.replace("/developers/", "");
        if ("new".equalsIgnoreCase(id)) {
            req.setAttribute("developer", new Developer());
            req.setAttribute("isNew", true);
            req.setAttribute("listCompany", CompanyService.getInstance().getAll());
            req.getRequestDispatcher("/jsp/developer.jsp").forward(req, resp);
            return;
        }
        Optional<Developer> developerOpt = service.get(Long.parseLong(id));
        if (developerOpt.isPresent()) {
            Developer developer = developerOpt.get();
            req.setAttribute("developer", developer);
            req.setAttribute("listCompany", CompanyService.getInstance().getAll());
            req.setAttribute("getCompany", CompanyService.getInstance());
            req.getRequestDispatcher("/jsp/developer.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/developers");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Developer.class)
            .ifPresent(developer -> service.update(developer));
        System.out.println(resp.getStatus());
        resp.sendRedirect("/developers");
    }

}
