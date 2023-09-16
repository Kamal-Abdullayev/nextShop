package com.nextShop.product.controller;

import com.nextShop.product.dto.colorDto.ColorRequest;
import com.nextShop.product.dto.colorDto.ColorResponse;
import com.nextShop.product.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/colors")
public class ColorController {
    private final ColorService colorService;

    @GetMapping("/all")
    public ResponseEntity<List<ColorResponse>> getColorList() {
        return new ResponseEntity<>(colorService.getColorList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
        public ResponseEntity<ColorResponse> getColorDtoById(@PathVariable("id") String id) {
        return new ResponseEntity<>(colorService.getColorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveColor(@Valid @RequestBody ColorRequest colorDtoRequest) {
        return new ResponseEntity<>(colorService.saveColor(colorDtoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorResponse> updateColor(@PathVariable("id") String id, @Valid @RequestBody ColorRequest colorDtoRequest) {
        return new ResponseEntity<>(colorService.updateColor(id, colorDtoRequest), HttpStatus.OK);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateColor(@PathVariable("id") String id) {
        colorService.deactivateColor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteColor(@PathVariable("id") String id) {
        colorService.deleteColor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateColor(@PathVariable("id") String id) {
        colorService.activateColor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/deactivated")
    public ResponseEntity<List<ColorResponse>> getAllDeactivatedColors() {
        return new ResponseEntity<>(colorService.getAllDeactivatedColors(), HttpStatus.OK);
    }
}
