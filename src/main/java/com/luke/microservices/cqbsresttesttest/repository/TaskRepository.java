package com.luke.microservices.cqbsresttesttest.repository;

import com.luke.microservices.cqbsresttesttest.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
