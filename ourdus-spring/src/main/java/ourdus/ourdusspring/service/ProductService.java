package ourdus.ourdusspring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Category;
import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.CategoryRepository;
import ourdus.ourdusspring.repository.CommentRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository,
                          CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    public Page<Product> findAll(@PageableDefault(size=10, page=0) Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Optional<Product> findOne(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        product.get().setHit(product.get().getHit()+1);
        //조회시 조회수 올리기
        return product;
    }

    public Optional<List<Product>> findAllByCategory(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }


    public Product save(Product product, Long authorId, Long categoryId)//product info save
    {
        User author = userRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("작가의 아이디가 잘못되었습니다."));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("작품 카테고리의 아이디가 잘못되었습니다."));
        Product saveProduct = Product.builder()
                                    .name(product.getName())
                                    .price(product.getPrice())
                                    .optionNum(product.getOptionNum())
                                    .category(category)
                                    .author(author)
                                    .build();
        Product result = productRepository.save(saveProduct);
        return result;
    }

    public String delete(Long productId) {
        productRepository.deleteById(productId);
//        Optional<Product> findProduct = productRepository.findById(productId);
//        if(findProduct.isPresent()){
//            productRepository.delete(productId);
//            return "작품 삭제 성공";
//        }else{
//            throw new NoSuchElementException("존재하지 않는 작품은 삭제할 수 없습니다.");
//        }
        return "작품 삭제 성공";
    }

    public Product modify(Long productId,Product product, Long categoryId) {
        Product result = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 작품은 수정할 수 없습니다."));
        if(categoryId != result.getCategory().getId() && categoryId != null){
            result.setCategory(categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NoSuchElementException("잘못된 카테고리 아이디입니다.")));
        }
        result.setName(product.getName());
        result.setPrice(product.getPrice());
        result.setOptionNum(product.getOptionNum());
        return result;
    }

    public Comment addComment(Comment comment, Long productId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("해당하는 유저가 없습니다."));
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new NoSuchElementException("해당하는 작품이 없습니다."));
        comment.setProduct(product);
        comment.setUser(user);
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> getCommentList(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new NoSuchElementException("해당하는 작품이 없습니다."));
        return product.getCommentList();
    }

    public String removeComment(Long commentId){
        commentRepository.deleteById(commentId);
        return "comment delete success";
    }

    public Page<Product> rateOrderList ()
    {
        Pageable pageable =PageRequest.of(0,10,Sort.Direction.DESC,"rate");
        return productRepository.findAll(pageable);
    }

    public Page<Product> hitOrderList ()
    {
        Pageable pageable =PageRequest.of(0,10,Sort.Direction.DESC,"hit");
        return productRepository.findAll(pageable);
    }

    public Page<Product> priceOrderList ()
    {
        Pageable pageable =PageRequest.of(0,10,Sort.Direction.DESC,"price");
        return productRepository.findAll(pageable);
    }

    public Page<Product> purchaseOrderList ()
    {
        Pageable pageable =PageRequest.of(0,10,Sort.Direction.DESC,"purchase");
        return productRepository.findAll(pageable);
    }


}
