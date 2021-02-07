package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Cart;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.CartRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void save(Cart cart, Long userId, Long productId){
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("사용자 정보를 찾을 수 없습니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("카트에 넣으려는 작품이 존재하지 않습니다."));
        Cart saveCart = Cart.defaultBuilder()
                            .user(user)
                            .product(product)
                            .optionInfo(cart.getOptionInfo())
                            .productNum(cart.getProductNum())
                            .build();
        cartRepository.save(saveCart);
    }

    public List<Cart> findAllByUserId(Long userId){
        return cartRepository.findAllByUserId(userId).orElse(null);
    }

    public void delete(Long cartId){
        cartRepository.deleteById(cartId);
    }


}
