package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardatabaseApplication {

    private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    /*
    Ici je peux faire une injection meme si je n'ai pas défini encore une implemntation de l'interface, puisque dans
    tout le projet il y a un seul Bean qui implemente l'interface #CrudRepository#, c est la calss #SimpleJpaRepository#
    qui fait partie du starter #spring-boot-starter-data-jpa# qui sera disponible
     */
    @Autowired
    private CarRepository repository;

    @Autowired
    private OwnerRepository orepository;

    public static void main(String[] args) {
        // after adding comment here
        SpringApplication.run(CardatabaseApplication.class, args);
        logger.info("Car database Application is Started");
    }

    // this interface allows us to execute additional code before the application has fully started
    @Bean
    CommandLineRunner runner() {
        return args -> {
            // add owner objects and save these to database
            Owner owner1 = new Owner("Jhon", "Jonson");
            Owner owner2 = new Owner("Mary", "Robinson");
            orepository.save(owner1);
            orepository.save(owner2);

            // add car objects with link to owners and save these to database
            repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owner1));
            repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owner2));
            repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owner2));
        };
    }
}
