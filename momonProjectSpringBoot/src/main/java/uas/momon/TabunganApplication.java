package uas.momon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TabunganApplication {
	public static void main(String[] args) {
		SpringApplication.run(TabunganApplication.class, args);
		//TabunganApplication = nama class di main
	}
}
