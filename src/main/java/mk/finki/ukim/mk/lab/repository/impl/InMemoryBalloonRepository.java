package mk.finki.ukim.mk.lab.repository.impl;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Repository
public class InMemoryBalloonRepository {

    private List<Balloon> balloonList = null;

    @PostConstruct
    public void init() {
        this.balloonList = new ArrayList<>();

        balloonList.add(new Balloon("Star Balloon", "Gold", new Manufacturer("Bobo", "China", "address 1")));
        balloonList.add(new Balloon("Circle Balloon", "Blue", new Manufacturer("Bobo", "China", "address 1")));
        balloonList.add(new Balloon("Heart Balloon", "Red", new Manufacturer("New Shine", "China", "address 2")));
        balloonList.add(new Balloon("Bee Balloon", "Red", new Manufacturer("China Luna", "Vietnam", "address 4")));
        balloonList.add(new Balloon("Elephant Balloon", "Pink", new Manufacturer("Peng Wei", "Singapore", "address 5")));
        balloonList.add(new Balloon("Chick Balloon", "Yellow", new Manufacturer("Bobo", "China", "address 1")));
        balloonList.add(new Balloon("Ladybug Balloon", "Red with black spots", new Manufacturer("Peng Wei", "Singapore", "address 5")));
        balloonList.add(new Balloon("Lion Balloon", "Yellow", new Manufacturer("Global Inflatables", "Taiwan", "address 3")));
        balloonList.add(new Balloon("Butterfly Balloon", "Pink, Blue and Yellow", new Manufacturer("Global Inflatables", "Taiwan", "address 3")));
        balloonList.add(new Balloon("Unicorn Balloon", "White", new Manufacturer("New Shine", "China", "address 2")));
    }

    public List<Balloon> findAllBalloons() {
        return balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloonList.stream().filter(b -> b.getName().contains(text) || b.getDescription().contains(text)).collect(Collectors.toList());
    }

    public boolean deleteById(Long id) {
        return balloonList.removeIf(b -> b.getId().equals(id));
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {
        balloonList.removeIf(x -> x.getName().equals(name));
        Balloon balloon = new Balloon(name, description, manufacturer);
        balloonList.add(balloon);
        return Optional.of(balloon);
    }

    public Optional<Balloon> findById(Long id) {
        return balloonList.stream().filter(x -> x.getId().equals(id)).findFirst();
    }
}
