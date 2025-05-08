package com.backendapp.faturabcknd.controller;

import com.backendapp.faturabcknd.model.Fatura;
import com.backendapp.faturabcknd.repo.FaturaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/faturas")
@CrossOrigin(origins = "*")
public class FaturaController {

    @Autowired
    private FaturaRepo repo;

    @PostMapping("/addFatura")
    public ResponseEntity<String> addFatura(@RequestBody Fatura fatura) {
        //    	 System.out.println("Gelen Fatura Detayları:");
        //         System.out.println("Product Number: " + fatura.getProductNumber());
        //         System.out.println("Product Name: " + fatura.getProductName());
        //         System.out.println("Count: " + fatura.getCount());
        //         System.out.println("Unit: " + fatura.getUnit());
        //         System.out.println("Unit Price: " + fatura.getUnitPrice());
        //         System.out.println("Total Amount: " + fatura.getTotalAmount());
        try {
            repo.save(fatura);  
            return new ResponseEntity<>("Fatura başarıyla eklendi", HttpStatus.CREATED);
        } catch (Exception e) {
           
            // e.printStackTrace(); 
            return new ResponseEntity<>("Fatura eklenirken bir hata oluştu: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fatura> getFaturaById(@PathVariable long id) {
        return repo.findById(id)
                .map(fatura -> new ResponseEntity<>(fatura, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Fatura>> getAllFaturas() {
        try {
            List<Fatura> faturalar = repo.findAll();
            if (faturalar.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(faturalar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fatura> updateFatura(@PathVariable long id, @RequestBody Fatura faturaDetails) {
        Optional<Fatura> faturaData = repo.findById(id);

        if (faturaData.isPresent()) {
            Fatura _fatura = faturaData.get();
            _fatura.setProductNumber(faturaDetails.getProductNumber());
            _fatura.setProductName(faturaDetails.getProductName());
            _fatura.setCount(faturaDetails.getCount());
            _fatura.setUnit(faturaDetails.getUnit());
            _fatura.setUnitPrice(faturaDetails.getUnitPrice());
            _fatura.setTotalAmount(faturaDetails.getTotalAmount());
           
            return new ResponseEntity<>(repo.save(_fatura), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFatura(@PathVariable long id) {
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return new ResponseEntity<>("Fatura başarıyla silindi", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Silinecek fatura bulunamadı", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Fatura silinirken bir hata oluştu: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}