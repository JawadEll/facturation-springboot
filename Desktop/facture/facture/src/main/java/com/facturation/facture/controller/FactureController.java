package com.facturation.facture.controller;

import com.facturation.facture.dto.FactureCreationDTO;
import com.facturation.facture.dto.FactureExportDTO;
import com.facturation.facture.model.Facture;
import com.facturation.facture.service.FactureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Facture", description = "Partie ====> Facture")
@RequestMapping("/factures/api") // pour Swagger
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    /*GET /factures/api */
    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {
        return ResponseEntity.ok(factureService.getAllFactures());
    }

    /* api POST /factures/api/{id} 1 ou 2 ... */
    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
        Facture facture = factureService.getFactureById(id);
        if (facture == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facture);
    }

    /* api POST /factures/api */
    @PostMapping

    public ResponseEntity<Facture> createFacture(@RequestBody FactureCreationDTO dto) {
        Facture saved = factureService.createFactureAvecDTO(dto);
        return ResponseEntity.ok(saved);
          }

    //all export facture
          @Tag(name = "FactureComplete   ", description = "Patie ===> Facture Complete_exporter")
          @GetMapping("/{id}/export")

    public ResponseEntity<FactureExportDTO> exportFacture(@PathVariable Long id) {
        FactureExportDTO dto = factureService.exporterFactureEnJSON(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

   @Tag(name = "All facturation ")
    @GetMapping("/export")
    public List<FactureExportDTO> exporterToutesFactures() {
        return factureService.getAllFacturesExport();
    }

}
