package ourdus.ourdusspring.service.product.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.order.Cart;
import ourdus.ourdusspring.repository.product.order.CartRepository;
import ourdus.ourdusspring.service.product.ProductService;
import ourdus.ourdusspring.service.user.UserService;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    public CartService(CartRepository cartRepository, ProductService productService, UserService userService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public Cart save(Cart cart, Long userId, Long productId){
        cart.setUser(userService.findUser(userId));
        cart.setProduct(productService.findById(productId));
        return cartRepository.save(cart);
    }

    @Transactional(readOnly = true)
    public List<Cart> findAllByUserId(Long userId){
        return cartRepository.findAllByUserId(userId).orElse(Collections.emptyList());
    }

    public void delete(Long cartId, Long userId){
        Cart cart = findById(cartId);
        cart.validOwner(userId);
        cartRepository.delete(cart);
    }

    @Transactional(readOnly = true)
    public Cart findById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 정보를 찾을 수 없습니다."));
    }

}
