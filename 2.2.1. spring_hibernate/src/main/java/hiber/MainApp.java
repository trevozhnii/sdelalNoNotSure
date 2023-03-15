package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Ivan", "Ivanov", "ivanovi@mail.ru", new Car("mazda", 3)));
      userService.add(new User("Petr", "Petrov", "petrovp@mail.ru", new Car("bmw", 5)));
      userService.add(new User("Rodion", "Rodionov", "rodionovr@mail.ru", new Car("nissan", 350)));
      userService.add(new User("Sveta", "Svetikova", "svetikovas@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println(user.getCar());
         System.out.println();
      }

      System.out.println("Ищем владельца bmw 5...");
      List<User> usersByCarModel = userService.getUsersByCarModel("bmw", 5);
      for (User user : usersByCarModel) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println(user.getCar());
         System.out.println();
      }

      context.close();
   }
}
