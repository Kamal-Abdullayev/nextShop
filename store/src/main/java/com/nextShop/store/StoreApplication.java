package com.nextShop.store;

import com.nextShop.store.model.Address;
import com.nextShop.store.model.Brand;
import com.nextShop.store.model.Store;
import com.nextShop.store.repository.AddressRepository;
import com.nextShop.store.repository.BrandRepository;
import com.nextShop.store.repository.StoreRepository;
import com.nextShop.store.service.AddressService;
import com.nextShop.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class StoreApplication implements CommandLineRunner {
	private final StoreRepository storeRepository;
	private final AddressRepository addressRepository;
	private final BrandRepository brandRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Address address = new Address("Azerbaijan", "Baku", "Test 123, Baku Azerbaijan", "AZ2000");
		Address address2 = new Address("Czech Republic", "Bruno", "Lorem ipsum 245, Baku Czech Republic", "CZK1298");
		address.setActive(true);
		address2.setActive(true);
		addressRepository.save(address);
		addressRepository.save(address2);


		Store store = new Store("nextShop - Azerbaijan", List.of(address));
		Store store2 = new Store("nextShop - Czech Republic", List.of(address2));
		store.setActive(true);
		store2.setActive(true);
		storeRepository.save(store);
		storeRepository.save(store2);


		Brand brand = new Brand("nextShop", DESCRIPTION, LOGO.getBytes(), Set.of(store));
		Brand brand2 = new Brand("nextShopOriginal", DESCRIPTION, LOGO.getBytes(), Set.of(store2));
		brand.setActive(true);
		brand2.setActive(true);
		brandRepository.save(brand);
		brandRepository.save(brand2);
	}


	private static final String DESCRIPTION = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when took a galley of make a type specimen book. It has survived not only five centuries Lorem Ipsum.";
	private static final String LOGO = "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";
}