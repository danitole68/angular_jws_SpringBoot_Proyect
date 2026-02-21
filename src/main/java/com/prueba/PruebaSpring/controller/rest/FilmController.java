package com.prueba.PruebaSpring.controller.rest;

import com.prueba.PruebaSpring.models.Films;
import com.prueba.PruebaSpring.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    // Obtener todas las películas

    @GetMapping
    public List<Films> getAllFilms() {
        return filmService.getFilms();
    }

    // Obtener una película por ID

    @GetMapping("/{id}")
    public ResponseEntity<Films> getFilmById(@PathVariable Long id) {
        return filmService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear o actualizar una película

    @PostMapping
    public ResponseEntity<Object> createOrUpdateFilm(@RequestBody Films film) {
        if (film.getId() == null) { // Crear nueva película
            if (filmService.findByTitle(film.getTitle()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El título ya existe. No se puede agregar la película.");
            }
            Films newFilm = filmService.addFilm(film);
            return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
        } else { // Actualizar película existente
            Optional<Films> existingFilm = filmService.findByTitle(film.getTitle());
            if (existingFilm.isPresent() && !(existingFilm.get().getId() == film.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El título ya pertenece a otra película.");
            }
            return ResponseEntity.ok(filmService.updateFilm(film.getId(), film));
        }
    }

    // Eliminar una película por ID

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}