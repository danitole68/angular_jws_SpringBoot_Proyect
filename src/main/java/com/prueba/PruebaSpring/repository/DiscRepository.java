package com.prueba.PruebaSpring.repository;

import com.prueba.PruebaSpring.models.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface DiscRepository extends JpaRepository<Disc, Long> {

    List<Disc> findByTitle(String title);

    @Query("SELECT d FROM Disc d WHERE LOWER(d.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Disc> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT d FROM Disc d WHERE LOWER(d.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Disc> findByAuthorContainingIgnoreCase(@Param("author") String author);

    List<Disc> findByYear(LocalDate year);

    List<Disc> findByTracks(int tracks);
}