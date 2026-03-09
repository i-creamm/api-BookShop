package ecom.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CartDTO {

    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone is required")
    private String phone;
}
