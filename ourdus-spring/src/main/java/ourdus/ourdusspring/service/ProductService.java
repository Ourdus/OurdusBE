package ourdus.ourdusspring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Category;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.CategoryRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Optional<Product> findOne(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        return product;
    }

    public Optional<List<Product>> findAllByCategory(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }


    public Product save(Product product, Long authorId, Long categoryId)//product info save
    {
        User user = userRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("작가의 아이디가 잘못되었습니다."));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("작품 카테고리의 아이디가 잘못되었습니다."));
        Product saveProduct = Product.builder()
                                    .name(product.getName())
                                    .price(product.getPrice())
                                    .optionNum(product.getOptionNum())
                                    .category(category)
                                    .user(user)
                                    .build();
        Product result = productRepository.save(saveProduct);
        return result;
    }

    public String delete(Long productId) {
        if (!productRepository.existsById(productId)) {//해당 id가 존재하지 않는 경우 처리
            throw new NoSuchElementException("존재하지 않는 작품은 삭제할 수 없습니다.");
        }
       Optional <Product> result= productRepository.findById(productId);
       return "delete success";
    }

    public Product update(Product product, Long categoryId) {
        Product result = productRepository.findById(product.getId()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 작품은 수정할 수 없습니다."));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("작품 카테고리의 아이디가 잘못되었습니다."));

        result.setCategory(category);
        result.setOptionNum(product.getOptionNum());
        result.setName(product.getName());
        result.setPrice(product.getPrice());
        return result;
    }

}
