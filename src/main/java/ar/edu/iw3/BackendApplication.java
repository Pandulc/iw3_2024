package ar.edu.iw3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.iw3.model.Product;
import ar.edu.iw3.model.business.IProductBusiness;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication 
@Slf4j
public class BackendApplication implements CommandLineRunner{

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
		
	}

	@Autowired
	private IProductBusiness productBusiness;
	
	@Override
	public void run(String... args) throws Exception {
		

		try {
			
			Product p1=new Product(1, "Leche", true, 150);
			productBusiness.update(p1);

		} catch (Exception e) {
			log.warn(e.getMessage());
		}

	}

}
