package ecom.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private int quantity;
	private Double price;
	private int discount;
	private String image;
	@Column(length = 1000)
	private String description;
	private LocalDate enteredDate;
	private boolean status;
	private int sold;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}

//sold enteredDate
