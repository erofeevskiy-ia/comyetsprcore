package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.util.Date;


@Configuration
public class UtilConf {

    @Bean
    public Date data() {
        return new Date();
    }

    @Bean
    public DateFormat df() {
        return DateFormat.getDateTimeInstance();
    }

}
