package com.railwaycompany.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


//    @Override
//    public void onStartup(ServletContext servletCxt) {
//
//        // Load Spring web application configuration
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebMvcConfig.class);
//        context.refresh();
//
//        // Create and register the DispatcherServlet
//        DispatcherServlet servlet = new DispatcherServlet(context);
//        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/app/*");
//    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
