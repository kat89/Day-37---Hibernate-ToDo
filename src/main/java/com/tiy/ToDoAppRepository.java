package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kat on 5/17/2016.
 */
public interface ToDoAppRepository extends CrudRepository<ToDoItemList, Integer> {

    List<ToDoItemList> findByUser(User user);

    @Query("SELECT g FROM ToDoItemList g WHERE g.text LIKE ?1%")
    List<ToDoItemList> findByNameStartsWith(String name);


}



