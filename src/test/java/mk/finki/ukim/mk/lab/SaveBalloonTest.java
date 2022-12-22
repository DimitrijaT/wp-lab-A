package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.NoBalloonDescriptionException;
import mk.finki.ukim.mk.lab.model.exceptions.NoBalloonNameException;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.impl.BalloonServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SaveBalloonTest {

    private BalloonServiceImpl service;

    @Mock
    private BalloonRepository balloonRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;


    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);

        Manufacturer manufacturer = new Manufacturer("manufacturerName", "manufacturerCountry", "manufacturerAddress");

        Balloon balloon = new Balloon("balloonName", "balloonDescription", manufacturer);

        Mockito.when(this.manufacturerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(manufacturer));

        Mockito.doNothing().when(this.balloonRepository).deleteByName(Mockito.anyString());

        Mockito.when(this.balloonRepository.save(Mockito.any(Balloon.class))).thenReturn(balloon);


        service = Mockito.spy(new BalloonServiceImpl(this.balloonRepository, this.manufacturerRepository));
    }

    @Test
    public void testSaveWithNullName() {

        Assert.assertThrows("NoBalloonNameException",
                NoBalloonNameException.class,
                () -> this.service.save(null, "description", 1L));

        Mockito.verify(this.service).save(null, "description", 1L);
    }

    @Test
    public void testSaveWithEmptyName() {
        Assert.assertThrows("NoBalloonNameException",
                NoBalloonNameException.class,
                () -> this.service.save("", "balloonDescription", 1L));

        Mockito.verify(this.service).save("", "balloonDescription", 1L);
    }

    @Test
    public void testSaveWithNullDescription() {
        Assert.assertThrows("NoBalloonDescriptionException",
                NoBalloonDescriptionException.class,
                () -> this.service.save("balloonName", null, 1L));

        Mockito.verify(this.service).save("balloonName", null, 1L);
    }

    @Test
    public void testSaveWithEmptyDescription() {
        Assert.assertThrows("NoBalloonDescriptionException",
                NoBalloonDescriptionException.class,
                () -> this.service.save("balloonName", "", 1L));

        Mockito.verify(this.service).save("balloonName", "", 1L);
    }

    @Test
    public void testSaveWithInvalidManufacturerId() {
        Mockito.when(this.manufacturerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assert.assertThrows("ManufacturerNotFoundException",
                ManufacturerNotFoundException.class,
                () -> this.service.save("name", "description", 1L));
        Mockito.verify(this.service).save("name", "description", 1L);

    }

    @Test
    public void testSaveSuccess() {
        Optional<Balloon> savedBalloon = service.save("name", "description", 1L);
        Assert.assertTrue(savedBalloon.isPresent());
        Assert.assertEquals("balloonName", savedBalloon.get().getName());
        Assert.assertEquals("balloonDescription", savedBalloon.get().getDescription());
        Assert.assertEquals("manufacturerName", savedBalloon.get().getManufacturer().getName());
    }

}
