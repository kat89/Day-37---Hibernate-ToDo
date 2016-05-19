package com.tiy;

import javax.persistence.*;

/**
 * Created by kat on 5/17/2016.
 */
@Entity
@Table(name = "items")
public class ToDoItemList {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    boolean isDone;

    @ManyToOne
    User user;

    public ToDoItemList() {
    }

    public ToDoItemList (String text, boolean isDone, User user) {
        this.text = text;
        this.isDone = isDone;
        this.user = user;
    }
}
