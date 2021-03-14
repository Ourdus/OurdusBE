package ourdus.ourdusspring.repository.product.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.order.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart save(Cart cart);
    Optional<List<Cart>> findAllByUserId(Long id);
}
