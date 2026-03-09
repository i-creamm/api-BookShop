package ecom.controller;


import ecom.dto.CartDTO;
import ecom.entity.Cart;
import ecom.repository.CartRepository;
import ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{email}")
    public ResponseEntity<Cart> getCartUser (@PathVariable("email") String email){
        return ResponseEntity.ok(cartService.findCartByUser(email));
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<Cart> putCartUser (@PathVariable("email") String email, @RequestBody CartDTO request){
        return ResponseEntity.ok(cartService.updateCartByUser(email,request));
    }

}
