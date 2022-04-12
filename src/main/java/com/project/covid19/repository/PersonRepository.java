package com.project.covid19.repository;

import com.project.covid19.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    <T> Page<T> findAllBy(Class<T> newsVOClass, Pageable pageable);
}
