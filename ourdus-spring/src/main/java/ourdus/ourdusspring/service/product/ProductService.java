package ourdus.ourdusspring.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.ProductOrderCategory;
import ourdus.ourdusspring.domain.product.category.Category;
import ourdus.ourdusspring.domain.product.comment.Comment;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.repository.product.ProductRepository;
import ourdus.ourdusspring.repository.product.category.CategoryRepository;
import ourdus.ourdusspring.repository.product.comment.CommentRepository;
import ourdus.ourdusspring.service.user.UserService;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
public class ProductService {
    
    private final String NOT_FOUND_PRODUCT = "해당하는 작품이 없습니다.";
    private final String NOT_FOUND_CATEGORY = "해당하는 작품 카테고리가 없습니다.";
    private final String NOT_FOUND_COMMENT = "해당하는 댓글이 없습니다.";

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public ProductService(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product view(Long productId) {
        checkNotNull(productId);
        Product product = findById(productId);
        product.changeHit();
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public Product save(Product product, Long authorId, Long categoryId) {
        checkNotNull(product);
        checkNotNull(authorId);
        checkNotNull(categoryId);
        User user = userService.findUser(authorId);
        user.validAuthor();
        product.setAuthor(user);
        product.setCategory(findCategoryById(categoryId));
        return productRepository.save(product);
    }

    public String delete(Long productId, Long authorId) {
        Product product = findById(productId);
        product.validAuthor(authorId);
        productRepository.delete(product);
        return "작품 삭제 성공";
    }

    public Product modify(Long productId, Product product, Long categoryId) {
        checkNotNull(productId);
        checkNotNull(product);
        checkNotNull(categoryId);
        Product result = findById(productId);
        if (result.isChangeCategory(categoryId)) {
            result.setCategory(findCategoryById(categoryId));
        }

        result.edit(product);
        return result;
    }

    public Comment addComment(Comment comment, Long productId, Long userId) {
        checkNotNull(comment);
        checkNotNull(productId);
        checkNotNull(userId);
        comment.setProduct(findById(productId));
        comment.setUser(userService.findUser(userId));
        commentRepository.save(comment);
        return comment;
    }

    public String removeComment(Long commentId, Long userId) {
        Comment comment = findCommentById(commentId);
        comment.validOwner(userId);
        commentRepository.delete(comment);
        return "comment delete success";
    }

    @Transactional(readOnly = true)
    public List<Comment> findCommentList(Long productId) {
        return findById(productId).getCommentList();
    }

    @Transactional(readOnly = true)
    public Page<Product> orderByList(ProductOrderCategory category) {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, category.getCategory());
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_PRODUCT));
    }

    @Transactional(readOnly = true)
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_CATEGORY));
    }

    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMENT));
    }
}
