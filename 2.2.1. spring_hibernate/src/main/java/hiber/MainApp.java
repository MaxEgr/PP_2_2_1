package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("bmw", 520);
      Car car2 = new Car("mersedes", 320);
      Car car3 = new Car("skoda", 525);
      Car car4 = new Car("bmw", 640);

      User user1 = new User("Maksim", "Egorov", "MksEgr@");
      User user2 = new User("Maria", "Sidorova", "MS@");
      User user3 = new User("Kosty", "Ivanov", "KI@");
      User user4 = new User("Vera", "Lukina", "VL@");

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1.");
      }

      System.out.println(userService.getUser("2", 2));
      System.out.println("2.");

      try {
         userService.getUser("3", 3);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3.");


      }

      context.close();
   }
}
