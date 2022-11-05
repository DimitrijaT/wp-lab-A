package mk.finki.ukim.mk.lab.repository;


import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    private List<Balloon> balloonList = null;

    @PostConstruct
    public void init() {
        this.balloonList = new ArrayList<>();

        balloonList.add(new Balloon("Star Balloon", "Gold"));
        balloonList.add(new Balloon("Circle Balloon", "Blue"));
        balloonList.add(new Balloon("Heart Balloon", "Red"));
        balloonList.add(new Balloon("Bee Balloon", "Red"));
        balloonList.add(new Balloon("Elephant Balloon", "Pink"));
        balloonList.add(new Balloon("Chick Balloon", "Yellow"));
        balloonList.add(new Balloon("Ladybug Balloon", "Red with black spots"));
        balloonList.add(new Balloon("Lion Balloon", "Yellow"));
        balloonList.add(new Balloon("Butterfly Balloon", "Pink, Blue and Yellow"));
        balloonList.add(new Balloon("Unicorn Balloon", "White"));
    }

    public List<Balloon> findAllBalloons() {
        return balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloonList.stream().filter(b -> b.getName().contains(text) || b.getDescription().contains(text)).collect(Collectors.toList());
    }
}
