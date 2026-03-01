package ecom.service.impl;

import ecom.entity.User;
import ecom.repository.UserRepository;
import ecom.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public Boolean checkByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

}
