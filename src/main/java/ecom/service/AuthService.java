package ecom.service;

import ecom.entity.User;

public interface AuthService {

    Boolean checkByEmail(String email);

    void save (User user);
}
