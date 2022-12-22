package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();

    List<Balloon> searchByNameOrDescription(String text);

    boolean deleteById(Long id);

    Optional<Balloon> edit(Long balloonId, String name, String description, Long manufacturerId);

    Optional<Balloon> save(String name, String description, Long manufacturerId);


    Optional<Balloon> findById(Long id);
}
