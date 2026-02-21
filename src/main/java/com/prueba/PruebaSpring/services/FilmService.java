package com.prueba.PruebaSpring.services;

import com.prueba.PruebaSpring.Enum.Format;
import com.prueba.PruebaSpring.models.Films;
import com.prueba.PruebaSpring.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    // Obtener todas las películas
    public List<Films> getFilms() {
        return filmRepository.findAll();
    }

    // Buscar película por ID
    public Optional<Films> findById(Long id) {
        return filmRepository.findById(id);
    }

    // Buscar película por título (exacto)
    public Optional<Films> findByTitle(String title) {
        return filmRepository.findByTitle(title).stream().findFirst();
    }

    // Buscar películas que contengan el título (parcial)
    public List<Films> findByTitleContaining(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }

    // Buscar películas por director
    public List<Films> findByDirectorContaining(String director) {
        return filmRepository.findByFilmDirectorContainingIgnoreCase(director);
    }

    // Buscar películas por año
    public List<Films> findByYear(Integer year) {
        return filmRepository.findByYear(year);
    }

    // Buscar películas por formato
    public List<Films> findByFormat(Format format) {
        return filmRepository.findByFormat(format);
    }

    // Añadir una nueva película
    public Films addFilm(Films film) {
        return filmRepository.save(film);
    }

    // Actualizar una película existente
    public Films updateFilm(Long id, Films filmDetails) {
        return filmRepository.findById(id).map(film -> {
            film.setTitle(filmDetails.getTitle());
            film.setFormat(filmDetails.getFormat());
            film.setYear(filmDetails.getYear());
            film.setPrice(filmDetails.getPrice());
            film.setFilmDirector(filmDetails.getFilmDirector());
            return filmRepository.save(film);
        }).orElseThrow(() -> new RuntimeException("Película no encontrada con ID " + id));
    }

    // Eliminar una película por ID
    public boolean deleteFilm(Long id) {
        try {
            filmRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Verificar si el título es único (para evitar duplicados)
    public boolean isTitleUnique(String title) {
        return filmRepository.findByTitle(title).isEmpty();
    }
}