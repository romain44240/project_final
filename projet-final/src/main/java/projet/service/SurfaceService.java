package projet.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOSurface;
import projet.model.Reservation;
import projet.model.Surface;
import projet.request.SurfaceRequest;
import projet.response.SurfaceResponse;

@Service
public class SurfaceService {

    @Autowired
    private IDAOSurface daoSurface;

    
    public List<SurfaceResponse> getAll() {
        return daoSurface.findAll().stream()
                .map(SurfaceResponse::convert)
                .collect(Collectors.toList());
    }

    
    public SurfaceResponse getById(Integer id) {
        Surface surface = daoSurface.findById(id)
                .orElseThrow(() -> new RuntimeException("Surface non trouvée avec id : " + id));
        return SurfaceResponse.convert(surface);
    }

    
    public SurfaceResponse create(SurfaceRequest dto) {
        Surface surface = SurfaceRequest.convert(dto);
        Surface saved = daoSurface.save(surface);
        return SurfaceResponse.convert(saved);
    }

    
    public SurfaceResponse update(Integer id, SurfaceRequest dto) {
        Surface surface = daoSurface.findById(id)
                .orElseThrow(() -> new RuntimeException("Surface non trouvée avec id : " + id));
        
        surface.setCapacite(dto.getCapacite());
        surface.setCouleur(dto.getCouleur());

        Surface updated = daoSurface.save(surface);
        return SurfaceResponse.convert(updated);
    }

    
    public void delete(Integer id) {
        if (!daoSurface.existsById(id)) {
            throw new RuntimeException("Surface non trouvée avec id : " + id);
        }
        daoSurface.deleteById(id);
    }
    
    public List<SurfaceResponse> getReservedSurfacesByDateTime(LocalDateTime dateTime) {
        return daoSurface.findAll().stream()
                .filter(surface -> {
                    //vérifier si la surface a une réservation pour la date donnée
                    Reservation reservation = surface.getReservation();
                    if (reservation != null) {
                        //vérifier si la réservation est pour la même date et heure
                        return reservation.getDateReservation().equals(dateTime);
                    }
                    return false;
                })
                .map(SurfaceResponse::convert)
                .collect(Collectors.toList());
    }
}

