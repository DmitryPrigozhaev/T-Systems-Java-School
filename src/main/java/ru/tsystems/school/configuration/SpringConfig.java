package ru.tsystems.school.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.tsystems.school")
//@EnableWebMvc
@Import(HibernateConfig.class)
public class SpringConfig {

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
//
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
}