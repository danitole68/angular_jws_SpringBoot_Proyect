package com.prueba.PruebaSpring.services;

import com.prueba.PruebaSpring.models.Disc;
import com.prueba.PruebaSpring.repository.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiscService {

    @Autowired
    private DiscRepository discRepository;

    public List<Disc> getDiscs() {
        return discRepository.findAll();
    }

    public Optional<Disc> findById(Long id) {
        return discRepository.findById(id);
    }

    public Optional<Disc> findByTitle(String title) {
        return discRepository.findByTitle(title).stream().findFirst();
    }

    public List<Disc> findByTitleContaining(String title) {
        return discRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Disc> findByAuthorContaining(String author) {
        return discRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Disc> findByYear(LocalDate year) {
        return discRepository.findByYear(year);
    }

    public List<Disc> findByTracks(int tracks) {
        return discRepository.findByTracks(tracks);
    }

    public Disc addDisc(Disc disc) {
        return discRepository.save(disc);
    }

    public Disc updateDisc(Long id, Disc discDetails) {
        return discRepository.findById(id).map(disc -> {
            disc.setTitle(discDetails.getTitle());
            disc.setTracks(discDetails.getTracks());
            disc.setPrice(discDetails.getPrice());
            disc.setAuthor(discDetails.getAuthor());
            disc.setYear(discDetails.getYear());
            return discRepository.save(disc);
        }).orElseThrow(() -> new RuntimeException("Disco no encontrado"));
    }

    public boolean deleteDisc(Long id) {
        try {
            discRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}