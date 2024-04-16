package com.jungche.happyschool.repository;

import com.jungche.happyschool.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person readByEmail(String email);
}
