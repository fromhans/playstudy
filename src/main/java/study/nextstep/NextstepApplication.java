package study.nextstep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@SpringBootApplication
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFERRED)
public class NextstepApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextstepApplication.class, args);
	}

}
