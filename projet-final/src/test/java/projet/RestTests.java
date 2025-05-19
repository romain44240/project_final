package projet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import projet.request.ConsommableRequest;
import projet.request.JeuRequest;
import projet.request.ReservationRequest;
import projet.request.SurfaceRequest;
import projet.response.ConsommableResponse;
import projet.response.JeuResponse;
import projet.response.ReservationResponse;
import projet.response.SurfaceResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// ATTENTION, LES TESTS MODIFIENT LA BDD, IL FAUT BASCULER SUR UNE BASE H2 POUR TESTER SANS RISQUES DE MODIFICATION
// @ActiveProfiles("test")
public class RestTests {
    
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate template;

    private HttpHeaders headers = new HttpHeaders();

    private RestTests() {
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setBasicAuth("JulienL", "employe1");
    }

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    // ------------------------------------------------- //
    // -------------------- SURFACE -------------------- //
    // ------------------------------------------------- //

    
    @Test
    void getAllSurfaces() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/surface"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void createSurface() throws Exception {
        //
        SurfaceRequest surfaceRequest = new SurfaceRequest();
        surfaceRequest.setCapacite(4);
        surfaceRequest.setCouleur("rouge");
        HttpEntity<SurfaceRequest> request = new HttpEntity<>(surfaceRequest, this.headers);

        //
        ResponseEntity<SurfaceResponse> surfaceResponse = template.postForEntity("/api/surface", request, SurfaceResponse.class);

        //
        assertEquals(HttpStatus.OK, surfaceResponse.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, surfaceResponse.getHeaders().getContentType());
        assertNotNull(surfaceResponse.getBody());
        assertEquals(4, surfaceResponse.getBody().getCapacite());
        assertEquals("rouge", surfaceResponse.getBody().getCouleur());
    }

    @Test
    void updateSurface() throws Exception {
        //
        SurfaceRequest initialRequest = new SurfaceRequest();
        initialRequest.setCapacite(4);
        initialRequest.setCouleur("rouge");
        HttpEntity<SurfaceRequest> createRequest = new HttpEntity<>(initialRequest, this.headers);

        ResponseEntity<SurfaceResponse> createResponse = template.postForEntity("/api/surface", createRequest, SurfaceResponse.class);
        Integer surfaceId = createResponse.getBody().getId();

        //
        SurfaceRequest updateRequest = new SurfaceRequest();
        updateRequest.setCapacite(6);
        updateRequest.setCouleur("bleu");
        HttpEntity<SurfaceRequest> request = new HttpEntity<>(updateRequest, this.headers);
        template.put("/api/surface/" + surfaceId, request);

        ResponseEntity<SurfaceResponse> updatedResponse = template.exchange(
            "/api/surface/" + surfaceId,
            HttpMethod.GET,
            new HttpEntity<>(this.headers),
            SurfaceResponse.class
        );

        //
        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        assertNotNull(updatedResponse.getBody());
        assertEquals(6, updatedResponse.getBody().getCapacite());
        assertEquals("bleu", updatedResponse.getBody().getCouleur());
    }

    @Test
    void deleteSurface() throws Exception {
        //
        SurfaceRequest surfaceRequest = new SurfaceRequest();
        surfaceRequest.setCapacite(4);
        surfaceRequest.setCouleur("rouge");
        HttpEntity<SurfaceRequest> createRequest = new HttpEntity<>(surfaceRequest, this.headers);
        
        ResponseEntity<SurfaceResponse> createResponse = template.postForEntity("/api/surface", createRequest, SurfaceResponse.class);
        Integer surfaceId = createResponse.getBody().getId();

        //
        template.exchange("/api/surface/" + surfaceId, HttpMethod.DELETE, new HttpEntity<>(this.headers), Void.class);

        //
        ResponseEntity<SurfaceResponse> surfaceResponse = template.exchange(
            "/api/surface/" + surfaceId,
            HttpMethod.GET,
            new HttpEntity<>(this.headers),
            SurfaceResponse.class
        );

        assertEquals(HttpStatus.NOT_FOUND, surfaceResponse.getStatusCode());
    }

    // ------------------------------------------------- //
    // -------------------- PRODUIT -------------------- //
    // ------------------------------------------------- //

    @Test
    void getAllJeux() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/produit/jeux"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void createJeu() throws Exception {
        JeuRequest jeuRequest = new JeuRequest();
        jeuRequest.setNom("Catan");
        jeuRequest.setPrix(39.99);
        jeuRequest.setStock(10);
        jeuRequest.setNbMin(3);
        jeuRequest.setNbMax(4);
        jeuRequest.setDuree(90);
        jeuRequest.setEditeur("Kosmos");
        jeuRequest.setUrlRegle("https://example.com/regles-catan.pdf");
        jeuRequest.setUrlImage("https://example.com/catan.jpg");

        HttpEntity<JeuRequest> request = new HttpEntity<>(jeuRequest, this.headers);
        ResponseEntity<JeuResponse> response = template.postForEntity("/api/produit/jeu", request, JeuResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Catan", response.getBody().getNom());
    }

    @Test
    void updateJeu() throws Exception {
        JeuRequest createRequest = new JeuRequest();
        createRequest.setNom("Catan");
        createRequest.setPrix(39.99);
        createRequest.setStock(10);
        createRequest.setNbMin(3);
        createRequest.setNbMax(4);
        createRequest.setDuree(90);
        createRequest.setEditeur("Kosmos");
        createRequest.setUrlRegle("https://example.com/regles-catan.pdf");
        createRequest.setUrlImage("https://example.com/catan.jpg");

        ResponseEntity<JeuResponse> createResponse = template.postForEntity("/api/produit/jeu", new HttpEntity<>(createRequest, this.headers), JeuResponse.class);
        Integer jeuId = createResponse.getBody().getId();

        JeuRequest updateRequest = new JeuRequest();
        updateRequest.setNom("Catan Deluxe");
        updateRequest.setPrix(49.99);
        updateRequest.setStock(15);
        updateRequest.setNbMin(3);
        updateRequest.setNbMax(4);
        updateRequest.setDuree(90);
        updateRequest.setEditeur("Kosmos");
        updateRequest.setUrlRegle("https://example.com/regles-catan.pdf");
        updateRequest.setUrlImage("https://example.com/catan-deluxe.jpg");

        template.put("/api/produit/jeu/" + jeuId, new HttpEntity<>(updateRequest, this.headers));

        ResponseEntity<JeuResponse> updatedResponse = template.exchange(
            "/api/produit/" + jeuId,
            HttpMethod.GET,
            new HttpEntity<>(this.headers),
            JeuResponse.class
        );

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        assertEquals("Catan Deluxe", updatedResponse.getBody().getNom());
        assertEquals(49.99, updatedResponse.getBody().getPrix());
    }

    @Test
    void deleteJeu() throws Exception {
        JeuRequest jeuRequest = new JeuRequest();
        jeuRequest.setNom("Catan");
        jeuRequest.setPrix(39.99);
        jeuRequest.setStock(10);
        jeuRequest.setNbMin(3);
        jeuRequest.setNbMax(4);
        jeuRequest.setDuree(90);
        jeuRequest.setEditeur("Kosmos");
        jeuRequest.setUrlRegle("https://example.com/regles-catan.pdf");
        jeuRequest.setUrlImage("https://example.com/catan.jpg");

        ResponseEntity<JeuResponse> createResponse = template.postForEntity("/api/produit/jeu", new HttpEntity<>(jeuRequest, this.headers), JeuResponse.class);
        Integer jeuId = createResponse.getBody().getId();

        template.exchange("/api/produit/" + jeuId, HttpMethod.DELETE, new HttpEntity<>(this.headers), Void.class);

        ResponseEntity<JeuResponse> getResponse = template.exchange(
            "/api/produit/" + jeuId,
            HttpMethod.GET,
            new HttpEntity<>(this.headers),
            JeuResponse.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }

    @Test
    void getAllConsommables() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/produit/consos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void createConsommable() throws Exception {
        ConsommableRequest requestObject = new ConsommableRequest();
        requestObject.setNom("Café");
        requestObject.setPrix(1.50);
        requestObject.setStock(100);

        HttpEntity<ConsommableRequest> request = new HttpEntity<>(requestObject, this.headers);
        ResponseEntity<ConsommableResponse> response = template.postForEntity("/api/produit/consommable", request, ConsommableResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Café", response.getBody().getNom());
    }

    @Test
    void updateConsommable() throws Exception {
        ConsommableRequest createRequest = new ConsommableRequest();
        createRequest.setNom("Café");
        createRequest.setPrix(1.50);
        createRequest.setStock(100);

        ResponseEntity<ConsommableResponse> createResponse = template.postForEntity("/api/produit/consommable", new HttpEntity<>(createRequest, this.headers), ConsommableResponse.class);
        Integer id = createResponse.getBody().getId();

        ConsommableRequest updateRequest = new ConsommableRequest();
        updateRequest.setNom("Thé");
        updateRequest.setPrix(2.00);
        updateRequest.setStock(80);

        template.put("/api/produit/consommable/" + id, new HttpEntity<>(updateRequest, this.headers));

        ResponseEntity<ConsommableResponse> updatedResponse = template.exchange(
            "/api/produit/" + id,
            HttpMethod.GET,
            new HttpEntity<>(this.headers),
            ConsommableResponse.class
        );

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        assertEquals("Thé", updatedResponse.getBody().getNom());
        assertEquals(2.00, updatedResponse.getBody().getPrix());
    }

    @Test
    void deleteConsommable() throws Exception {
        ConsommableRequest consommableRequest = new ConsommableRequest();
        consommableRequest.setNom("Café");
        consommableRequest.setPrix(1.50);
        consommableRequest.setStock(100);

        ResponseEntity<ConsommableResponse> createResponse = template.postForEntity("/api/produit/consommable", new HttpEntity<>(consommableRequest, this.headers), ConsommableResponse.class);
        Integer id = createResponse.getBody().getId();

        template.exchange("/api/produit/" + id, HttpMethod.DELETE, new HttpEntity<>(this.headers), Void.class);

        ResponseEntity<ConsommableResponse> getResponse = template.exchange(
            "/api/produit/" + id,
            HttpMethod.GET,
            new HttpEntity<>(this.headers),
            ConsommableResponse.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }

    // ----------------------------------------------------- //
    // -------------------- RESERVATION -------------------- //
    // ----------------------------------------------------- //

    @Test
    void createReservation() throws Exception {
        ReservationRequest requestDto = new ReservationRequest();
        requestDto.setDebut(LocalDateTime.parse("2025-05-19T12:30:00"));
        requestDto.setFin(LocalDateTime.parse("2025-05-19T14:30:00"));
        requestDto.setNbPersonne(3);
        requestDto.setIdClient(1);
        requestDto.setIdEmploye(3);
        requestDto.setIdSurface(3);
        requestDto.setIdJeu(3);

        HttpEntity<ReservationRequest> request = new HttpEntity<>(requestDto, this.headers);
        ResponseEntity<ReservationResponse> response = template.postForEntity("/api/reservation", request, ReservationResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().getNbPersonne());
        assertNotNull(response.getBody().getClient());
    }
}
