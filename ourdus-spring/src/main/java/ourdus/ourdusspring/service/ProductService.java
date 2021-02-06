package ourdus.ourdusspring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Category;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.ProductCreateDTO;
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

    //TODO 정보값을 넘겨받을떄 dto가 아닌 다른 vo로 선언해주어야함 (product 생성시에 필요한 dto 정보들)
    public Product save(ProductCreateDTO dto, Long authorId, Long categoryId)//product info save
    {
        User user = userRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("작가의 아이디가 잘못되었습니다."));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("작품 카테고리의 아이디가 잘못되었습니다."));
        Product saveProduct = Product.builder()
                                    .name(dto.getName())
                                    .price(dto.getPrice())
                                    .optionNum(dto.getOptionNum())
                                    .category(category)
                                    .user(user)
                                    .build();
        Product result = productRepository.save(saveProduct);
        return result;
    }
}
