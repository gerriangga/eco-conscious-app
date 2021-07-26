package com.example.EcoConsciousApp;

import com.example.EcoConsciousApp.entity.User;
import com.example.EcoConsciousApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class EcoConsciousAppApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcoConsciousAppApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @PostConstruct
//    public void initUsers() {
//        List<User> users = Stream.of(new User(1, "gerriangga", "admin", "gerriangga@gmail.com"),
//                new User(2, "matiusrimbe", "admin", "matiusrimbe@gmail.com"),
//                new User(3, "megasilvia", "admin", "megasilvia@gmail.com"))
//                .collect(Collectors.toList());
//        userRepository.saveAll(users);
//    }
}
