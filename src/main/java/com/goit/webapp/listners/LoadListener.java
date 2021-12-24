package com.goit.webapp.listners;

import com.goit.config.DbMigration;
import com.goit.service.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class LoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbMigration.migrate();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("companyDao", CompanyService.getInstance());
        servletContext.setAttribute("companyService", CompanyService.getInstance());
        servletContext.setAttribute("customerDao", CustomerService.getInstance());
        servletContext.setAttribute("customerService", CustomerService.getInstance());
        servletContext.setAttribute("developerDao", DeveloperService.getInstance());
        servletContext.setAttribute("developerService", DeveloperService.getInstance());
        servletContext.setAttribute("projectService", ProjectService.getInstance());
        servletContext.setAttribute("skillDao", SkillService.getInstance());
        servletContext.setAttribute("skillService", SkillService.getInstance());
    }
}
