package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.StockDto.ProductRequest;
import com.contractormanagemet.Contractor.Magement.DTO.StockDto.ProductResponse;
import com.contractormanagemet.Contractor.Magement.DTO.StockDto.StockUsageResponse;
import com.contractormanagemet.Contractor.Magement.Entity.Product;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import com.contractormanagemet.Contractor.Magement.Entity.StockUsage;
import com.contractormanagemet.Contractor.Magement.Repository.ProductRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ProjectRepository;
import com.contractormanagemet.Contractor.Magement.Repository.StockUsageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private StockUsageRepository usageRepository;
    @Autowired private ProjectRepository projectRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    //    public ProductResponse createProduct(ProductRequest request) {
//        Product product = new Product();
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//        product.setTotalQuantityString(request.getTotalQuantityString());
//        product.setProductAddOnDate(request.getProductAddOnDate());
//
//        // Extract numeric quantity
//        int quantityValue = extractNumericValue(request.getTotalQuantityString());
//        product.setTotalQuantityValue(quantityValue);
//
//        if (request.getProjectId() != null) {
//            Project project = projectRepository.findById(request.getProjectId())
//                    .orElseThrow(() -> new RuntimeException("Project not found"));
//            product.setProject(project);
//        }
//
//        product = productRepository.save(product);
//
//        return mapToResponse(product);
//    }
//
//private ProductResponse mapToResponse(Product product) {
//    int used = product.getUsages().stream().mapToInt(StockUsage::getQuantityUsed).sum();
//    int remaining = product.getTotalQuantityValue() - used;
//
//    List<StockUsageResponse> usageResponses = product.getUsages().stream()
//            .map(usage -> {
//                StockUsageResponse usageResponse = new StockUsageResponse();
//                usageResponse.setId(usage.getId());
//                usageResponse.setQuantityUsed(usage.getQuantityUsed());
//                usageResponse.setUsedAt(usage.getUsedAt());
//                return usageResponse;
//            })
//            .collect(Collectors.toList());
//
//    ProductResponse.ProjectSummary projectSummary = null;
//    if (product.getProject() != null) {
//        projectSummary = new ProductResponse.ProjectSummary(
//                product.getProject().getId(),
//                product.getProject().getName()
//        );
//    }
//
//    return new ProductResponse(
//            product.getId(),
//            product.getName(),
//            product.getPrice(),
//            product.getTotalQuantityString(),
//            product.getTotalQuantityValue(),
//            product.getProductAddOnDate(),
//            usageResponses,  // <-- pass mapped List<StockUsageResponse>
//            projectSummary,
//            used,
//            remaining
//    );
//}
//
public Product createProduct(ProductRequest request) {
    Product product = new Product();
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setTotalQuantityString(request.getTotalQuantityString());
    product.setProductAddOnDate(request.getProductAddOnDate());

    // Extract quantity value
    if (request.getTotalQuantityString() != null && !request.getTotalQuantityString().isEmpty()) {
        int quantityValue = extractNumericValue(request.getTotalQuantityString());
        product.setTotalQuantityValue(quantityValue);
    }

    // Set project
    if (request.getProjectId() != null) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        product.setProject(project);
    }

    return productRepository.save(product);
}

    private int extractNumericValue(String input) {
        try {
            String numberOnly = input.replaceAll("[^0-9]", "").trim();
            return Integer.parseInt(numberOnly);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid quantity format: " + input);
        }
    }

    public Product addStock(Long productId, String quantityString,Double price) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int quantityToAdd = extractNumericValue(quantityString);
        int updatedQuantity = product.getTotalQuantityValue() + quantityToAdd;
        double totalprice=product.getPrice()+price;

        String unit = quantityString.replaceAll("[0-9]", "").trim();

        product.setTotalQuantityValue(updatedQuantity);
        product.setTotalQuantityString(updatedQuantity + " " + unit);
        product.setPrice(totalprice);

        return productRepository.save(product);
    }


    public Product useStock(Long productId, int quantityUsed) {
        logger.debug("Received request to use {} units of stock for product ID {}", quantityUsed, productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getRemainingQuantity() < quantityUsed) {
            logger.warn("Insufficient stock: requested={}, available={}", quantityUsed, product.getRemainingQuantity());
            throw new RuntimeException("Insufficient stock available");
        }

        StockUsage usage = new StockUsage();
        usage.setProduct(product);
        usage.setQuantityUsed(quantityUsed);
        usage.setUsedAt(LocalDateTime.now());

        usageRepository.save(usage);

        logger.debug("Stock usage recorded: {} units used for product ID {}", quantityUsed, productId);
        return product;
    }


    public List<StockUsageResponse> getUsageHistory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return product.getUsages().stream()
                .map(usage -> {
                    StockUsageResponse res = new StockUsageResponse();
                    res.setId(usage.getId());
                    res.setQuantityUsed(usage.getQuantityUsed());
                    res.setUsedAt(usage.getUsedAt());
                    return res;
                })
                .collect(Collectors.toList());

    }

    public Product getProductStockDetails(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToAllProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToAllProductResponse(Product product) {
        int used = product.getUsages().stream()
                .mapToInt(StockUsage::getQuantityUsed)
                .sum();
        int remaining = product.getTotalQuantityValue() - used;

        List<StockUsageResponse> usageResponses = product.getUsages().stream()
                .map(usage -> {
                    StockUsageResponse res = new StockUsageResponse();
                    res.setId(usage.getId()); // <--- Set the id here
                    res.setQuantityUsed(usage.getQuantityUsed());
                    res.setUsedAt(usage.getUsedAt());
                    return res;
                }).collect(Collectors.toList());

        ProductResponse.ProjectSummary projectSummary = null;
        if (product.getProject() != null) {
            projectSummary = new ProductResponse.ProjectSummary(
                    product.getProject().getId(),
                    product.getProject().getName()
            );
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getTotalQuantityString(),
                product.getTotalQuantityValue(),
                product.getProductAddOnDate(),
                usageResponses,
                projectSummary,
                used,
                remaining
        );
    }


    public List<ProductResponse> getAllProductByProjectId(Long projectId) {
        List<Product> products = productRepository.findByProjectId(projectId);
        return products.stream()
                .map(this::mapToAllProductResponse)
                .collect(Collectors.toList());
    }
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    public Product updateProduct(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setTotalQuantityString(request.getTotalQuantityString());
        product.setProductAddOnDate(request.getProductAddOnDate());

        if (request.getTotalQuantityString() != null && !request.getTotalQuantityString().isEmpty()) {
            int quantityValue = extractNumericValue(request.getTotalQuantityString());
            product.setTotalQuantityValue(quantityValue);
        }

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            product.setProject(project);
        }

        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
