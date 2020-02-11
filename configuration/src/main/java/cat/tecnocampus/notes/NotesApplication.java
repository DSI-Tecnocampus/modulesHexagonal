package cat.tecnocampus.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cat.tecnocampus.service", "cat.tecnocampus.webAdapter", "cat.tecnocampus.persistenceAdapter",
        "cat.tecnocampus.notes.security"})
public class NotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }
}

