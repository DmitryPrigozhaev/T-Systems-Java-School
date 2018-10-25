package com.railwaycompany.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.railwaycompany")
@EnableWebMvc
@Import(HibernateConfig.class)
public class WebMvcConfig implements WebMvcConfigurer {

    /*
     *   Вначале диспатчер сервлет (диспетчер) получает запрос, далее он смотрит свои настройки,
     *   чтобы понять какой контроллер использовать.
     *
     *   После получения имени контроллера запрос передается в Controller.
     *   В контроллере происходит обработка запроса и обратно посылается ModelAndView
     *
     *   Диспатчер сервлет на основании полученного ModelAndView ищет какое представление
     *   ему использовать (View Resolver) и получает в ответе имя представления View
     *
     *   В представление передаются данные (model) и обратно, если необходимо, посылается ответ от представления.
     */

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views");
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}