package click.escuela.student.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StudentCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCoreApplication.class, args);
	}

}
