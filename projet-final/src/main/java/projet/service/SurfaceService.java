package projet.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOReservation;
import projet.dao.IDAOSurface;
import projet.model.Reservation;
import projet.model.Surface;
import projet.request.SurfaceRequest;
import projet.response.SurfaceResponse;

@Service
public class SurfaceService {

    @Autowired
    private IDAOSurface daoSurface;
    
    @Autowired
    private IDAOReservation daoReservation;

    
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
    
    public List<SurfaceResponse> getSurfacesDisponibles(LocalDateTime debut, LocalDateTime fin) {
        
        List<Reservation> incluses = daoReservation.findByDebutLessThanAndFinGreaterThan(fin, debut);
        
        List<Surface> listeSurfaces = daoSurface.findAll();
        for(Reservation r : incluses) {
        	listeSurfaces.remove(r.getSurface());
        }
        
        return listeSurfaces.stream().map(SurfaceResponse::convert).collect(Collectors.toList());
    }

    
}

