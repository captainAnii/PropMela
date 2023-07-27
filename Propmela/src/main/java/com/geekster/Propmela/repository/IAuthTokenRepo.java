package com.geekster.Propmela.repository;



import com.geekster.Propmela.model.AuthenticationToken;
import com.geekster.Propmela.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByUser(User user);

    AuthenticationToken findFirstByTokenValue(String authTokenValue);

}
