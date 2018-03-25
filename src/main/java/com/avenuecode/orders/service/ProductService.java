package com.avenuecode.orders.service;

import com.avenuecode.orders.domain.Product;
import com.avenuecode.orders.repository.ProductRepository;
import com.avenuecode.orders.specification.product.ProductSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String productId) {
        return productRepository.findOne(productId);
    }

    public List<Product> searchCriteria(String search) {
        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find())
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));

        Specification<Product> spec = builder.build();

        return productRepository.findAll(spec);
    }
}
