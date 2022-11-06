package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

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
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        if (text == null || text.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public boolean deleteById(Long id) {
        return balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long id) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(id).orElseThrow(() -> new ManufacturerNotFoundException(id));
        return this.balloonRepository.save(name, description, manufacturer);
    }

    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }


}
