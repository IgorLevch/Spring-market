package ru.geekbrains.spring.spring.market.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.spring.market.entities.GroupEntity;

import java.util.Optional;


@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    @Query("select g from GroupEntity g where g.title = ?1")
    Optional<GroupEntity> findByTitle(String title);



}
