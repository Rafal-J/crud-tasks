package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.mysql.fabric.xmlrpc.base.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
    @Override
    List<Task> findAll();

    @Query
    List<Task> findById(Long id);

}