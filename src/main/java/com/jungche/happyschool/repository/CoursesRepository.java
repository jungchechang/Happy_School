package com.jungche.happyschool.repository;

import com.jungche.happyschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
}
