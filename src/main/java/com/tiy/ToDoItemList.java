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

    public ToDoItemList() {
    }

    public ToDoItemList (String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }
}
