package configurations;

import app.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource(value = {"classpath:client.properties"},
        ignoreResourceNotFound = true)
public class ClientConf {

    @Value("${id}")
    String id;

    @Value("${name}")
    String name;

    @Value("${greeting}")
    String greeting;


    @Bean
    @Scope(scopeName = "prototype")
    public Client client() {
        return new Client(id, name, greeting);
    }
}
