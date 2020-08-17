package pl.travel360;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class CountriesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountriesManagerApplication.class, args);
	}

}
