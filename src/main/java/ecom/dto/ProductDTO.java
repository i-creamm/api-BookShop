package ecom.dto;

import ecom.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private int quantity;
    @NotNull(message = "Price can't null")
    private Double price;
    private int discount;
    private String description;
    @NotBlank(message = "Status can't empty")
    private Category category;
}
