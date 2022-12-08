package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class InMemoryManufacturerRepository {

    private List<Manufacturer> manufacturers;


    @PostConstruct
    public void init() {
        manufacturers = new ArrayList<>();

        manufacturers.add(new Manufacturer("Bobo", "China", "address 1"));
        manufacturers.add(new Manufacturer("New Shine", "China", "address 2"));
        manufacturers.add(new Manufacturer("Global Inflatables", "Taiwan", "address 3"));
        manufacturers.add(new Manufacturer("China Luna", "Vietnam", "address 4"));
        manufacturers.add(new Manufacturer("Peng Wei", "Singapore", "address 5"));

    }

    public List<Manufacturer> findAll() {
        return this.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturers.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

}
