import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import javax.servlet.Filter;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    @Bean
    FilterRegistrationBean myFilterRegistration () {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        Filter filter = new StrutsPrepareAndExecuteFilter();
        frb.setFilter(filter);
        frb.setUrlPatterns(Arrays.asList("/*"));
        return frb;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
