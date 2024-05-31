package ru.comavp.javaeeexample.utils;

import lombok.experimental.UtilityClass;
import ru.comavp.javaeeexample.model.dto.ProductDto;
import ru.comavp.javaeeexample.model.entity.Product;

import java.util.List;

@UtilityClass
public class ProductMapper {

    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public List<ProductDto> mapToDto(List<Product> productList) {
        return productList.stream()
                .map(ProductMapper::mapToDto)
                .toList();
    }

    public Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
