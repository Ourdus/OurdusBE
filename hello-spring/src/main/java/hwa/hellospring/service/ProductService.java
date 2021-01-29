package hwa.hellospring.service;

import hwa.hellospring.domain.Member;
import hwa.hellospring.domain.Product;
import hwa.hellospring.repository.MemberRepository;
import hwa.hellospring.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }


    public int save(Product product)//product info save
    {
        productRepository.save(product);
        return product.getProduct_id();
    }

    public List<Product> productList ()//for all list return
    {
        return productRepository.findAll();
    }

}
