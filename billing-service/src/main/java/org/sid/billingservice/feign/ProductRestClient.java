package org.sid.billingservice.feign;

import org.sid.billingservice.entities.ProductItem;

import org.sid.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;


@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping("/products")
    PagedModel<ProductItem> pageProducts(@RequestParam int page,@RequestParam int size);


    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id);

}
