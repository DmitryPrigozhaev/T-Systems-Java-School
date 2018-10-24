package ru.tsystems.school.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    /*  Либо расширить AbstractAnnotationConfigDispatcherServletInitializer
     *  и переопределить методы:
     *  protected Class<?>[] getRootConfigClasses()
     *  protected Class<?>[] getServletConfigClasses()
     *  protected String[] getServletMappings()
     */


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(context));

        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
