package ecom.service;

import ecom.dto.CartDTO;
import ecom.entity.Cart;


public interface CartService {

    Cart findCartByUser (String email);

    Cart updateCartByUser (String email, CartDTO cart);

    void save (Cart cart);
}
