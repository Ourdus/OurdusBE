package ourdus.ourdusspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.repository.ProductRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long productId){
        Optional<Product> product = productRepository.findOneById(productId);
        return product;
    }

    public Optional<List<Product>> findAllByCategory(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }

}
