package com.geekster.Propmela.service;

import com.geekster.Propmela.model.Dealer;
import com.geekster.Propmela.repository.DealerRepository;
import org.springframework.stereotype.Service;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;

    // Constructor injection
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public Dealer createDealer(Dealer dealer) {
        // Implement dealer creation logic with necessary validations
        return dealerRepository.save(dealer);
    }

    public Dealer getDealerById(Long id) {
        // Implement logic to retrieve a dealer by their ID
        return dealerRepository.findById(id).orElse(null);
    }

    public Dealer updateDealer(Long id, Dealer dealer) {
        // Implement logic to update dealer data by ID
        Dealer existingDealer = dealerRepository.findById(id).orElse(null);
        if (existingDealer != null) {
            // Perform necessary updates to the existingDealer object based on the provided dealer data
            // For example: existingDealer.setName(dealer.getName());
            return dealerRepository.save(existingDealer);
        }
        return null;
    }

            public boolean deleteDealer(Long id) {
                // Implement logic to delete a dealer by ID
                Dealer dealer = dealerRepository.findById(id).orElse(null);
                if (dealer != null) {
                    dealerRepository.delete(dealer);
                    return true;
                }
                return false;
            }

            // Implement other dealer-related service methods
        }