package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    @Query(value = "SELECT * FROM ads WHERE title LIKE '%title%'", nativeQuery = true)
    List<Ad> findByTitleLikeIgnoreCase(@Param("title") String title);
}
