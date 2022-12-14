package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.NoBalloonDescriptionException;
import mk.finki.ukim.mk.lab.model.exceptions.NoBalloonNameException;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        if (text == null || text.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return this.balloonRepository.findAllByNameOrDescription(text, text);
    }

    @Override
    public boolean deleteById(Long id) {
        this.balloonRepository.deleteById(id);
        return balloonRepository.findById(id).isEmpty();
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        if (name == null || name.isEmpty())
            throw new NoBalloonNameException();
        if (description == null || description.isEmpty())
            throw new NoBalloonDescriptionException();
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(ManufacturerNotFoundException::new);

        this.balloonRepository.deleteByName(name);

        return Optional.of(this.balloonRepository.save(new Balloon(name, description, manufacturer)));
    }

    @Override
    @Transactional
    public Optional<Balloon> edit(Long balloonId, String name, String description, Long manufacturerId) {
        if (balloonId == null)
            throw new InvalidArgumentsException();
        if (description == null || description.isEmpty())
            throw new NoBalloonDescriptionException();
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(ManufacturerNotFoundException::new);

        Balloon balloon = this.balloonRepository.findById(balloonId).get();
        balloon.setName(name);
        balloon.setDescription(description);
        balloon.setManufacturer(manufacturer);

        return Optional.of(this.balloonRepository.save(balloon));
    }


    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }


}
