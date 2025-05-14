package projet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.model.Reservation;


public interface IDAOReservation extends JpaRepository<Reservation,Integer>{

    @Query("SELECT r FROM Reservation r WHERE r.client.id = :id")
    List<Reservation> getReservationsByClientId(@Param("id") Integer id);
}
