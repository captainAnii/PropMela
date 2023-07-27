package com.geekster.Propmela.service;

import com.geekster.Propmela.model.AuthenticationToken;
import com.geekster.Propmela.model.User;
import com.geekster.Propmela.model.dto.SignInInput;
import com.geekster.Propmela.model.dto.SignUpOutput;
import com.geekster.Propmela.repository.IAuthTokenRepo;
import com.geekster.Propmela.repository.UserRepository;
import com.geekster.Propmela.service.emailUtility.EmailHandler;
import com.geekster.Propmela.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    IAuthTokenRepo authTokenRepo;

    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null) {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this patient email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null) {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(user.getUserPassword());
            //saveAppointment the patient with the new encrypted password
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e) {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInUser(SignInInput signInInput) {

        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null) {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }

        //check if this patient email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;
        }
        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword)) {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authTokenRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }
    public String signOutUser(String email) {

        User user = userRepo.findFirstByUserEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByUser(user));
        return "User Signed out successfully";
    }

    public User getUserById(Long id) {
        // Implement logic to retrieve a user by their ID
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElse(null);
    }
    public User updateUser(Long id, User updatedUser) {
        // Retrieve the existing user from the database
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // Perform necessary updates to the existingUser object based on the provided updatedUser data
            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getUserEmail() != null) {
                existingUser.setUserEmail(updatedUser.getUserEmail());
            }
            if (updatedUser.getUserPassword() != null) {
                existingUser.setUserPassword(updatedUser.getUserPassword());
            }
            // Add more properties as needed

            // Save the updated user to the database
            return userRepo.save(existingUser);
        }
        return null; // Return null if the user with the given ID is not found
    }

}
