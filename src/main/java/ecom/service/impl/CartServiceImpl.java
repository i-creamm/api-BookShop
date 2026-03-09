package ecom.service.impl;

import ecom.dto.CartDTO;
import ecom.entity.Cart;
import ecom.entity.User;
import ecom.exception.BadRequestException;
import ecom.exception.NotFoundException;
import ecom.repository.CartRepository;
import ecom.repository.UserRepository;
import ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.accept.NotAcceptableApiVersionException;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;


    @Override
    public Cart findCartByUser (String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new BadRequestException("Email not found"));
        return cartRepository.findByUser(user)
                .orElseThrow(()-> new NotFoundException("Cart not found for this user"));
    }

    @Override
    public Cart updateCartByUser (String email, CartDTO cartDTO){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new BadRequestException("Email not found"));
        Cart existingCart = cartRepository.findByUser(user)
                .orElseThrow(()-> new NotFoundException("Cart not found for this user"));
        modelMapper.map(cartDTO, existingCart);

        existingCart.setUser(user);

        return cartRepository.save(existingCart);
    }

    @Override
    public void save (Cart cart){ cartRepository.save(cart); }
}
