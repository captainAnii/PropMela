package com.geekster.Propmela.controller;

import com.geekster.Propmela.model.Dealer;
import com.geekster.Propmela.service.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dealers")
public class DealerController {
    private final DealerService dealerService;

    // Constructor injection
    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    public ResponseEntity<Dealer> createDealer(@RequestBody Dealer dealer) {
        Dealer createdDealer = dealerService.createDealer(dealer);
        return new ResponseEntity<>(createdDealer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable Long id) {
        Dealer dealer = dealerService.getDealerById(id);
        if (dealer != null) {
            return new ResponseEntity<>(dealer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Long id, @RequestBody Dealer dealer) {
        Dealer updatedDealer = dealerService.updateDealer(id, dealer);
        if (updatedDealer != null) {
            return new ResponseEntity<>(updatedDealer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable Long id) {
        boolean deleted = dealerService.deleteDealer(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Implement other dealer-related CRUD operations
}