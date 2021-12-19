package com.flitx.scool;

import com.flitx.scool.model.Order;
import com.flitx.scool.model.School;
import com.flitx.scool.model.Status;
import com.flitx.scool.model.Student;
import com.flitx.scool.repo.OrderRepository;
import com.flitx.scool.repo.SchoolRepository;
import com.flitx.scool.repo.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class ScoolApplication implements CommandLineRunner {

    private final SchoolRepository schoolRepo;
    private final OrderRepository orderRepo;
    private final TransactionRepository studentRepo;


    @Autowired
    public ScoolApplication(SchoolRepository schoolRepo, OrderRepository orderRepo, TransactionRepository studentRepo) {
        this.schoolRepo = schoolRepo;
        this.orderRepo = orderRepo;
        this.studentRepo = studentRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScoolApplication.class, args);
    }



    @Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin","Content-Type",
                "Accept","Jwt-Token","Authorization","Origin, Accept","X-requested-With",
                "Access-Control-Request-Method","Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin","Content-Type","Accept","Jwt-Token","Authorization",
                "Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void run(String... args) throws Exception {


        School school = new School("1190", "Zamara");

        Student student = new Student("Matias Mendez Silva", "567826478");

        Student student2 = new Student("Alexander Quiroz  Barrios", "3716253769");


        Order order = new Order("ydqw7687", new BigDecimal("9537.00"), "SEPTIEMBRE", Status.PENDIENTE, LocalDateTime.now());
        orderRepo.save(order);

        Order order2 = new Order("jaksudy756", new BigDecimal("9537.00"), "OCTUBRE", Status.PAGADA, LocalDateTime.now());
        orderRepo.save(order2);

        List<Order> orders = new ArrayList<>();

        orders.add(order);
        orders.add(order2);
        student.setOrders(orders);

        Order order3 = new Order("asldouoia765", new BigDecimal("9537.00"), "NOVIEMBRE", Status.PAGADA, LocalDateTime.now());
        orderRepo.save(order3);

        Order order4 = new Order("jdhaiuysd67", new BigDecimal("9537.00"), "DICIEMBRE", Status.PENDIENTE, LocalDateTime.now());
        orderRepo.save(order4);




        List<Order> orders2 = new ArrayList<>();

        orders2.add(order3);
        orders2.add(order4);


        student2.setOrders(orders2);

        studentRepo.save(student);
        studentRepo.save(student2);



        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);


        school.setStudents(students);


        schoolRepo.save(school);

        //}


        for (School schooles : schoolRepo.findAll()) {

            System.out.println(schooles);
        }


    }


}
