package ourdus.ourdusspring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.*;
import ourdus.ourdusspring.repository.CategoryRepository;
import ourdus.ourdusspring.repository.CommentRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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

        Optional<Product> findProduct = productRepository.findById(productId);
        if(findProduct.isPresent()){
            productRepository.delete(productId);
            return "작품 삭제 성공";
        }else{
            throw new NoSuchElementException("존재하지 않는 작품은 삭제할 수 없습니다.");
        }
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

    public String deleteComment(Long productId, Long commentId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new NoSuchElementException("해당하는 작품이 없습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new NoSuchElementException("해당하는 댓글이 없습니다."));
        if(comment.getProduct().equals(product)){
            commentRepository.delete(comment);
            return "comment delete success";
        }else{
            throw new NoSuchElementException("comment delete fail");
        }
    }

    public String removeComment(Long commentId,Long productId){
        Product product = productRepository.findById(productId).get();
        if(product.getCommentList().removeIf(comment -> comment.getId().equals(commentId))){
            productRepository.save(product);
            commentRepository.delete(commentId);
            return "comment delete success";
        }else{
            throw new NoSuchElementException("comment delete fail");
        }
    }
}
