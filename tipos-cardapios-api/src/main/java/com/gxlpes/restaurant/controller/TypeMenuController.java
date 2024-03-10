package com.gxlpes.restaurant.controller;

import com.gxlpes.restaurant.model.TypeMenu;
import com.gxlpes.restaurant.service.TypeMenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tipos-cardapios")
public class TypeMenuController {

    private final TypeMenuService typeMenuService;

    public TypeMenuController(TypeMenuService typeMenuService) {
        this.typeMenuService = typeMenuService;
    }

    @PostMapping
    public ResponseEntity<?> saveTypeMenu(@RequestBody TypeMenu typeMenu) {
        try {
            TypeMenu savedTypeMenu = typeMenuService.saveOrUpdate(typeMenu);
            if (savedTypeMenu.getId() != null) {
                return ResponseEntity.created(
                        URI.create("/tipos-cardapios/id/" + savedTypeMenu.getId())).build();
            } else {
                return ResponseEntity.ok(savedTypeMenu);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable String id, @PathVariable String status) {
        try {
            String convertedStatus = status.equalsIgnoreCase("active") ? "A" : (status.equalsIgnoreCase("inactive") ? "I" : null);

            if (convertedStatus == null) {
                return ResponseEntity.badRequest().body("Invalid status value, either 'active' or 'inactive'");
            }

            TypeMenu updatedTypeMenu = typeMenuService.updateStatus(id, convertedStatus);
            return ResponseEntity.ok(updatedTypeMenu);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<TypeMenu>> listByName(
            @RequestParam(name = "nome", required = false) String name,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Page<TypeMenu> typeMenuPage;
            if (name == null || name.isEmpty()) {
                typeMenuPage = typeMenuService.listAllMenus(PageRequest.of(pagina, size));
            } else {
                typeMenuPage = typeMenuService.listByName(name, PageRequest.of(pagina, size));
            }
            return ResponseEntity.ok(typeMenuPage);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TypeMenu> findById(@PathVariable String id) {
        try {
            TypeMenu typeMenu = typeMenuService.findById(id);
            return ResponseEntity.ok(typeMenu);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTypeMenu(@PathVariable String id, @RequestBody TypeMenu typeMenu) {
        try {
            TypeMenu updatedTypeMenu = typeMenuService.saveOrUpdate(typeMenu);
            return ResponseEntity.ok(updatedTypeMenu);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
