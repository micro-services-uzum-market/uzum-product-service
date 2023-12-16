package org.example.uzummarket.product;

import lombok.RequiredArgsConstructor;
import org.example.uzummarket.common.exception.EntityNotFoundException;
import org.example.uzummarket.product.dto.ProductCreateDto;
import org.example.uzummarket.product.dto.ProductResponseDto;
import org.example.uzummarket.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    public ProductResponseDto create(ProductCreateDto productCreateDto) {
        Product entity = mapper.map(productCreateDto, Product.class);
        Product save = repository.save(entity);
        return mapper.map(save, ProductResponseDto.class);
    }

    public ProductResponseDto getId(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("product not found"));

        return mapper.map(product, ProductResponseDto.class);
    }

    public Page<ProductResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(product ->
                mapper.map(product, ProductResponseDto.class));
    }
}
