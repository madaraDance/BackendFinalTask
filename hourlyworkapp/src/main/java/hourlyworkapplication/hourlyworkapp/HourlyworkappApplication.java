package hourlyworkapplication.hourlyworkapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import hourlyworkapplication.hourlyworkapp.model.TimeLogRepository;
import hourlyworkapplication.hourlyworkapp.model.WorkType;
import hourlyworkapplication.hourlyworkapp.model.WorkTypeRepository;
import hourlyworkapplication.hourlyworkapp.model.AppUserRepository;
import hourlyworkapplication.hourlyworkapp.model.AppUser;
import hourlyworkapplication.hourlyworkapp.model.TimeLog;



@SpringBootApplication
public class HourlyworkappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HourlyworkappApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TimeLogRepository timeLogRepository, WorkTypeRepository workTypeRepository, AppUserRepository userRepository) {return (args) -> {

			workTypeRepository.save(new WorkType("Project Work"));
			workTypeRepository.save(new WorkType("Internal Development"));

			AppUser simpleUser = new AppUser("botUser", "$2a$10$66xTB/vFqIlrzD9w6m2jIeBys3kslEVxCwrOFjxRmiavGgExapOq6",//userpass
			"USER", "emptyemail070320251558@gmail.com");
			AppUser adminUser = new AppUser("admin", "$2a$10$H3VdlimXHqATZgEY7K2hj.6RR.UCppxeztnU0ECprdztpTHSGxS8i", //adminpass
			"ADMIN", "adminemail070320241602@gmail.com");

			userRepository.save(simpleUser);
			userRepository.save(adminUser);

			TimeLog firstTimeLog = new TimeLog("Working on a customer project", 8, 30, LocalDate.of(2024, 4, 20),
			userRepository.findByEmail("emptyemail070320251558@gmail.com"), workTypeRepository.findByName("Project Work"));	

			timeLogRepository.save(firstTimeLog);

		};

	}

}
