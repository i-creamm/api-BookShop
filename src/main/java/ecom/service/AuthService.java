package ecom.service;

import ecom.entity.User;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    Boolean checkByEmail(String email);

    void save (User user);
}
