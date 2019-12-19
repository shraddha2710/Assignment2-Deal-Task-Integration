package com.Assignment_2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.Assignment_2.Model.TaskResource;

@Repository
public interface TaskRepository extends  PagingAndSortingRepository<TaskResource, Long>{

}
