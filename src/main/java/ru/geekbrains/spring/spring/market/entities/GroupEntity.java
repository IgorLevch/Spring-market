package ru.geekbrains.spring.spring.market.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "groups")
@Data
public class GroupEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "group")
    private List<StudentEntity> students;

}
