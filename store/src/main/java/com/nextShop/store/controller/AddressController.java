package com.nextShop.store.controller;

import com.nextShop.store.dto.address.request.AddressRequest;
import com.nextShop.store.dto.address.response.AddressResponse;
import com.nextShop.store.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<List<AddressResponse>> getAddressList() {
        return new ResponseEntity<>(addressService.getAddressList(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable("id") String id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest brandRequest) {
        return new ResponseEntity<>(addressService.saveAddress(brandRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable("id") String id, @RequestBody AddressRequest brandRequest) {
        return new ResponseEntity<>(addressService.updateAddress(id, brandRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateAddress(@PathVariable("id") String id) {
        addressService.deactivateAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateAddress(@PathVariable("id") String id) {
        addressService.activateAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<AddressResponse>> getAllDeactivatedAddresses() {
        return new ResponseEntity<>(addressService.getAllDeactivatedAddresses(), HttpStatus.OK);
    }
}
