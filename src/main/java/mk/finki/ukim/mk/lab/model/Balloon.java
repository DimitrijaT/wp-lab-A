package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.Random;

@Data
public class Balloon {

    private String name;
    private String description;

    private Manufacturer manufacturer;

    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    private Long id;
    public Balloon(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }


}
