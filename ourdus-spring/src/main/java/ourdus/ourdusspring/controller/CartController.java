package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Cart;
import ourdus.ourdusspring.dto.CartDTO;
import ourdus.ourdusspring.service.CartService;
import ourdus.ourdusspring.service.JwtService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
public class CartController {

    @Autowired
    private JwtService jwtService;

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/t/w/cart/{user_id}")
    public ApiResult<List<CartDTO>> viewAllProduct(@PathVariable("user_id") Long userId /*HttpServletRequest req*/){
        //Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        List<Cart> carts = cartService.findAllByUserId(userId);
        List<CartDTO> cartDTOs = new ArrayList<>();
        carts.stream().forEach(cart -> {
            cartDTOs.add(new CartDTO(cart));
        });

        return OK(cartDTOs);
    }

    @PostMapping("/t/w/cart/delete/{cart_id}")
    public ApiResult<String> delete(@PathVariable("cart_id") Long cartId){
        cartService.delete(cartId);
        return OK("해당 카트를 삭제하였습니다.");
    }

    @PostMapping("/t/w/cart-in")
    public ApiResult<String> cartIn(/*HttpServletRequest req,*/ @RequestBody CartDTO cartDTO){
//        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        Long userId = 1L;
        Cart cart = Cart.createBuilder()
                .optionInfo(cartDTO.getOptionInfo())
                .productNum(cartDTO.getProductNum())
                .price(cartDTO.getProductDetailPrice())
                .build();
        cartService.save(cart, userId, cartDTO.getProductId());
        return OK("장바구니에 담겼습니다.");
    }

}
