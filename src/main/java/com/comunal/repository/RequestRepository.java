package com.comunal.repository;

import com.comunal.model.Request;
import com.comunal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findAllByUserOrderByDate(User user);
    List<Request> findAll();
}
