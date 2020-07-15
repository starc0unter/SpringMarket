package com.geekbrains.septembermarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public Category(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category role = (Category) o;
		return Objects.equals(id, role.id) &&
				Objects.equals(name, role.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
