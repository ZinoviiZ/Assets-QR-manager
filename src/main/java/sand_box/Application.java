package sand_box;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sand_box.services.interfaces.UserService;
import sand_box.tables.login.User;
import sand_box.tables.login.User_Role;

import java.util.Date;
import java.util.TimeZone;
//Start Spring boot apclication
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.addUser(new User("admin", "d033e22ae348aeb5660fc2140aec35850c4da997","zinuk14@gmail.com", User_Role.ADMIN, new Date(), new Date()));
//                userService.addUser(new User("user", "d033e22ae348aeb5660fc2140aec35850c4da997", User_Role.EDITOR));
            }
        };
    }
}