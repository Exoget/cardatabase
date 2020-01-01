package com.packt.cardatabase.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
a simple CRUD Fonctionalities to our entity class
There is also @PagingAndSortingRepository interface
you should have a look ( when dealing with a larger amounts of data)
*/
public interface CarRepository extends CrudRepository<Car, Long> {

    /*
    on peut ajouter nos propore requettes : pour cela il faut ajouter le prefix findBy + l'attribut de class
    on peut ajouter les jointure And ou bien OR
    on peut trier les resultats
     */

    // Fetch Cars by brand
    List<Car> findByBrand(String brand);

    // Fetch Cars by color
    List<Car> findByColor(String color);

    // Fetch Cars by year
    List<Car> findByYear(int year);

    // Fetch Cars by brand and model
    List<Car> findByBrandAndModel(String brand, String model);

    // Fetch Cars by brand or color
    List<Car> findByBrandOrColor(String brand, String color);

    // Fetch Cars by brand and sort by year
    List<Car> findByBrandOrderByYearAsc(String brand);

    // Fetch Cars by model using SQL
    @Query("select c from Car c where c.model = ?1")
    List<Car> findByModel(String model);

    // Fetch Cars by model using SQL
    @Query("select c from Car c where c.model like %?1")
    List<Car> findByModelEndsWith(String model);
}
