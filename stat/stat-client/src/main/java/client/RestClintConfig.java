package client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClintConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

    @Bean
    public StatClient statClient(RestClient restClient, @Value("${stat.url}") String statUrl) {
        return new StatClient(restClient, statUrl);
    }
}
