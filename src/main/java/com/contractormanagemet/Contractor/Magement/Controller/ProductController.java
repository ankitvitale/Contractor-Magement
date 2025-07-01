package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.StockDto.*;
import com.contractormanagemet.Contractor.Magement.Entity.Product;
import com.contractormanagemet.Contractor.Magement.Entity.StockUsage;
import com.contractormanagemet.Contractor.Magement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasRole('Admin')")

public class ProductController {

    @Autowired private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }


    @PostMapping("/{id}/add-stock")
    public ResponseEntity<Product> addStock(
            @PathVariable("id")  Long id,
            @RequestParam("quantityString") String quantityString,
            @RequestParam("price") double price)
             {
        return ResponseEntity.ok(productService.addStock(id, quantityString,price));
    }


    @PostMapping("/{id}/use-stock")
    public ResponseEntity<Product> useStock(
            @PathVariable("id")  Long id,
            @RequestBody UseStockRequest request) {
        return ResponseEntity.ok(productService.useStock(id, request.getQuantityUsed()));
    }


    @GetMapping("/{id}/stock")
    public ResponseEntity<Map<String, Object>> getStock(@PathVariable("id")  Long id) {
        Product product = productService.getProductStockDetails(id);
        Map<String, Object> result = new HashMap<>();
        result.put("productName", product.getName());
        result.put("sku", product.getId());
        result.put("totalQuantity", product.getTotalQuantityString());
        result.put("usedQuantity", product.getUsedQuantity());
        result.put("remainingQuantity", product.getRemainingQuantity() + " " +
                product.getTotalQuantityString().replaceAll("\\d+", "").trim());
        result.put("project", product.getProject() != null ? product.getProject().getId() : null);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/usages")
    public ResponseEntity<List<StockUsageResponse>> getUsageHistory(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(productService.getUsageHistory(id));
    }
    @GetMapping("/allProduct")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());

    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProductResponse>> getAllProductByProjectId(@PathVariable("projectId") Long projectId) {
        List<ProductResponse> products = productService.getAllProductByProjectId(projectId);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")  Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id")  Long id,
            @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }


    @GetMapping("/{id}/stock-in-history")
    public ResponseEntity<List<StockAdditionResponse>> getStockInHistory(@PathVariable("id")  Long id){
        return ResponseEntity.ok(productService.stockAdditionHistory(id));
    }
}
