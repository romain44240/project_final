package projet.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Reservation;
import projet.model.Surface;

public interface IDAOSurface extends JpaRepository<Surface,Integer> {

}
