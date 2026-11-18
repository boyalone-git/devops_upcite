package com.projet.annuaire;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/associations")
public class AnnuaireController {

    private final AnnuaireService annuaireService;
    private final RestClient restClient;

    public AnnuaireController(
            AnnuaireService annuaireService,
            @Value("${benevoles.service.url:http://service-benevoles:8081}") String benevolesUrl) {
        this.annuaireService = annuaireService;
        this.restClient = RestClient.builder().baseUrl(benevolesUrl).build();
    }

    @GetMapping
    public List getAssociations() {
        return annuaireService.getToutesLesAssociations();
    }

    @GetMapping("/synthese")
    public Map getSyntheseInterServices() {
        List associations = annuaireService.getToutesLesAssociations();
        try {
            Object[] benevolesArray = restClient.get()
                    .uri("/api/benevoles")
                    .retrieve()
                    .body(Object[].class);
            List benevoles = benevolesArray != null ? Arrays.asList(benevolesArray) : List.of();
            return Map.of(
                    "statutCommunication", "OK (service-annuaire vers service-benevoles:8081)",
                    "totalAssociations", associations.size(),
                    "totalBenevoles", benevoles.size(),
                    "associations", associations,
                    "benevoles", benevoles
            );
        } catch (Exception e) {
            return Map.of(
                    "statutCommunication", "ERREUR : " + e.getMessage(),
                    "associations", associations,
                    "benevoles", List.of()
            );
        }
    }

    @GetMapping("/{id}")
    public Association getAssociationById(@PathVariable String id) {
        return annuaireService.getAssociationParId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Association ajouterAssociation(@Valid @RequestBody Association nouvelleAssociation) {
        return annuaireService.ajouterAssociation(nouvelleAssociation);
    }

    @PutMapping("/{id}")
    public Association modifierAssociation(@PathVariable String id, @Valid @RequestBody Association detailsModifies) {
        return annuaireService.modifierAssociation(id, detailsModifies);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerAssociation(@PathVariable String id) {
        annuaireService.supprimerAssociation(id);
    }
}
