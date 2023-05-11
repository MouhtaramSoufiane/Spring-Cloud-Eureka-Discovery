package org.sid.inventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RepositoryInventory repositoryInventory, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
        repositoryRestConfiguration.exposeIdsFor(Product.class);
		repositoryInventory.save(new Product(null,"Ordinateur",12000,3));
			repositoryInventory.save(new Product(null,"SmartPhone",2000,5));
			repositoryInventory.save(new Product(null,"Imprimqnte",1000,14));

			repositoryInventory.findAll().forEach(p->{
				System.out.println(p.getName());
			});

		};
	}

}

@Entity  @NoArgsConstructor @AllArgsConstructor @ToString
@Data
class Product{
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private float price;
	private int quantity;

}

@RepositoryRestResource
interface RepositoryInventory extends JpaRepository<Product,Long> {


}