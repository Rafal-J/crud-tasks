import com.crud.tasks.controller.TaskController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskControllerFactory{

    @Bean
    public TaskController createTaskController() {
        return new TaskController();
    }
}