package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon, Long> {

    List<Balloon> findAllByName(String name);

    List<Balloon> findAllByDescription(String description);

    List<Balloon> findAllByNameOrDescription(String name,String description);

    void deleteById(Long id);

    void deleteByName(String name);

}
