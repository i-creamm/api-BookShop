package ecom.controller;

import ecom.common.ERole;
import ecom.config.JwtUtils;
import ecom.dto.LoginRequestDTO;
import ecom.dto.LoginResponseDTO;
import ecom.dto.RegisterDTO;
import ecom.entity.Cart;
import ecom.entity.Role;
import ecom.entity.User;
import ecom.exception.BadRequestException;
import ecom.repository.RoleRepository;
import ecom.service.AuthService;
import ecom.service.CartService;
import ecom.service.impl.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthService authService;
    private final CartService cartService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(
                new LoginResponseDTO(token)
        );
    }


    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDTO request){
        if(authService.checkByEmail(request.getEmail())){
            throw new BadRequestException("Email is already taken");
        }

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(true);
        user.setRoles(Set.of(userRole));
        user.setRegisterDate(LocalDate.now());
        authService.save(user);

        Cart cart = new Cart();
        cart.setAmount(0.0);
        cart.setAddress(user.getAddress());
        cart.setPhone(user.getPhone());
        cart.setUser(user);
        cartService.save(cart);
        return ResponseEntity.ok().build();
    }

}
