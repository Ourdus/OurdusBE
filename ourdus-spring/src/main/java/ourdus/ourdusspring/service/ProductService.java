package ourdus.ourdusspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findOne(Long productId){
        Product product = productRepository.findOneById(productId);
        return product;
    }

    public List<Product> findAllByCategory(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }

}
