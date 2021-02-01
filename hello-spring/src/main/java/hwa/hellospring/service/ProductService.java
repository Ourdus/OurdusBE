package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
import hwa.hellospring.domain.Product;
import hwa.hellospring.repository.MemberRepository;
import hwa.hellospring.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Optional<Product> save(Product product)//product info save
    {
        Optional<Product> products = productRepository.save(product);
        return products;
    }

    public List<Product> productList()//for all list return
    {
        return productRepository.findAll();
    }

    public Optional<Product> findOneById(int productId) {
        Optional<Product> product = productRepository.findOneById(productId);
        return product;
    }

   public int delete(int product_id)
    {
        return productRepository.delete(product_id);
    }
    public int modify(int product_id)
    {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + product_id));

        return productRepository.modify(product_id);
    }
    public List<Product> findAllByCategory(int category_id)//for all list return
    {
        return productRepository.findAllByCategory(category_id);
    }

}
