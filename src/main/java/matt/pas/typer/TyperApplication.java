package matt.pas.typer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class TyperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TyperApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory factory =
//                new HttpComponentsClientHttpRequestFactory();
//
//        factory.setReadTimeout(10000);

        return new RestTemplate();
    }
}
