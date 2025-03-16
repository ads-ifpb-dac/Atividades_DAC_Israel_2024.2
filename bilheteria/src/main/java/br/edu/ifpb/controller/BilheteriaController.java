package br.edu.ifpb.controller;

import br.edu.ifpb.model.*;
import br.edu.ifpb.service.BilheteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bilheteria")
public class BilheteriaController {

    @Autowired
    private BilheteriaService bilheteriaService;

    @PostMapping("/validar-ingresso")
    public ResponseEntity<String> validarIngresso(@RequestParam Long ingressoId) {
        try {
            bilheteriaService.validarIngresso(ingressoId);
            return ResponseEntity.ok("Ingresso validado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao validar ingresso: " + e.getMessage());
        }
    }
}