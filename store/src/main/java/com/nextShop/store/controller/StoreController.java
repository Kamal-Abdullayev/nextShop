package com.nextShop.store.controller;

import com.nextShop.store.dto.store.request.StoreRequest;
import com.nextShop.store.dto.store.response.StoreResponse;
import com.nextShop.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {
    private final StoreService storeService;
    @GetMapping("/all")
    public ResponseEntity<List<StoreResponse>> getStoreList() {
        return new ResponseEntity<>(storeService.getStoreList(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable("id") String id) {
        return new ResponseEntity<>(storeService.getStoreById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<StoreResponse> saveStore(@RequestBody StoreRequest brandRequest) {
        return new ResponseEntity<>(storeService.saveStore(brandRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable("id") String id, @RequestBody StoreRequest brandRequest) {
        return new ResponseEntity<>(storeService.updateStore(id, brandRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateStore(@PathVariable("id") String id) {
        storeService.deactivateStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStore(@PathVariable("id") String id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateStore(@PathVariable("id") String id) {
        storeService.activateStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<StoreResponse>> getAllDeactivatedStores() {
        return new ResponseEntity<>(storeService.getAllDeactivatedStores(), HttpStatus.OK);
    }
}
