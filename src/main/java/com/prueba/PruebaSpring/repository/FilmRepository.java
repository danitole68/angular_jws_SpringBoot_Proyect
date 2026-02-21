    package com.prueba.PruebaSpring.repository;

    import com.prueba.PruebaSpring.Enum.Format;
    import com.prueba.PruebaSpring.models.Films;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import java.time.LocalDate;
    import java.util.List;

    public interface FilmRepository extends JpaRepository<Films, Long> {

        // Buscar películas por título exacto
        List<Films> findByTitle(String title);

        // Buscar películas que contengan el título (ignorando mayúsculas/minúsculas)
        @Query("SELECT f FROM Films f WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :title, '%'))")
        List<Films> findByTitleContainingIgnoreCase(@Param("title") String title);

        // Buscar películas por director (ignorando mayúsculas/minúsculas)
        @Query("SELECT f FROM Films f WHERE LOWER(f.filmDirector) LIKE LOWER(CONCAT('%', :director, '%'))")
        List<Films> findByFilmDirectorContainingIgnoreCase(@Param("director") String director);

        // Buscar películas por año
        List<Films> findByYear(Integer year);

        // Buscar películas por formato
        List<Films> findByFormat(Format format);
    }