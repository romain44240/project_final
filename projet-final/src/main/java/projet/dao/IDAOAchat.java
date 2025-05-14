package projet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.model.Achat;


public interface IDAOAchat extends JpaRepository<Achat,Integer>{

    @Query("SELECT a FROM Achat a WHERE a.reservation.client.id = :id")
    List<Achat> getAchatsByClientId(@Param("id") Integer id);
}
