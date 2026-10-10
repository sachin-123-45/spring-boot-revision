package com.sachin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.entity.Task;


public interface TaskRepository extends JpaRepository<Task,Long> {

}
