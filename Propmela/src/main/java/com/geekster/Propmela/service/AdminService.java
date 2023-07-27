package com.geekster.Propmela.service;

import com.geekster.Propmela.model.Admin;
import com.geekster.Propmela.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    // Constructor injection
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin createAdmin(Admin admin) {
        // Implement admin creation logic with necessary validations
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        // Implement logic to retrieve an admin by their ID
        return adminRepository.findById(id).orElse(null);
    }

    public Admin updateAdmin(Long id, Admin admin) {
        // Implement logic to update admin data by ID
        Admin existingAdmin = adminRepository.findById(id).orElse(null);
        if (existingAdmin != null) {
            // Perform necessary updates to the existingAdmin object based on the provided admin data
            // For example: existingAdmin.setName(admin.getName());
            return adminRepository.save(existingAdmin);
        }
        return null;
    }

    public boolean deleteAdmin(Long id) {
        // Implement logic to delete an admin by ID
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            adminRepository.delete(admin);
            return true;
        }
        return false;
    }

    // Implement other admin-related service methods
}