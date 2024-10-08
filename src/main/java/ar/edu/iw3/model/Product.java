package ar.edu.iw3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100, unique = true)
	private String product;

	@Column(columnDefinition = "tinyint default 1")
	private boolean stock = true;

	private double price;

	// CATEGORIAS
	@ManyToOne
	@JoinColumn(name = "id_category", nullable = true)
	private Category category;

	// PROVEEDORES
	@ManyToMany
	@JoinTable(name = "products_suppliers", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_supplier"))
	private List<Supplier> suppliers = new ArrayList<>();

	@Override
	public String toString() {
		return String.format("id=%s, product=%s, precio=%s, stock=%s ", this.getId(), this.getProduct(), this.getPrice(), this.isStock());
	}
}
