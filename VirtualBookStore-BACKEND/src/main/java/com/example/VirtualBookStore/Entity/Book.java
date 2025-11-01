package com.example.VirtualBookStore.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public long id;

    @Column(name="title")
    public String title;

    @Column(name="author")
    public String author;

    @Column(name="price")
    public double price;

    @Column(name="category")
    public String category;

    @Column(name="stock")
    public int stock;

    @Column(name="description")
    public String description;

}
