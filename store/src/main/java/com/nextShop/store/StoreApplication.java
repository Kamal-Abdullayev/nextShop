package com.nextShop.store;

import com.nextShop.store.model.Address;
import com.nextShop.store.model.Brand;
import com.nextShop.store.model.Store;
import com.nextShop.store.repository.AddressRepository;
import com.nextShop.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class StoreApplication implements CommandLineRunner {
	private final StoreRepository storeRepository;
	private final AddressRepository addressRepository;
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Address address = new Address("Azerbaijan", "Baku", "Test 123, Baku Azerbaijan", "AZ2000");
		Address address2 = new Address("Czech Republic", "Bruno", "Lorem ipsum 245, Baku Czech Republic", "CZK1298");
		address.setActive(true);
		address2.setActive(true);
		addressRepository.save(address);
		addressRepository.save(address2);
		System.out.println(address.getId());
		System.out.println(address2.getId());

	}
}
