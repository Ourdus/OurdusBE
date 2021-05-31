package ourdus.ourdusspring.controller.product.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.order.Cart;
import ourdus.ourdusspring.dto.product.order.CartDTO;
import ourdus.ourdusspring.dto.product.order.CartRequest;
import ourdus.ourdusspring.service.product.order.CartService;

import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api(value = "카드 담기 관리")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ApiOperation(value = "장바구니 목록 조회", notes = "유저의 장바구니 목록을 조회한다.")
    @GetMapping("/t/w/cart")
    public ApiResult<List<CartDTO>> viewAllProduct(){
        List<CartDTO> carts = cartService.findAllByUserId(getAuthUserId())
                .stream()
                .filter(cart -> cart != null)
                .map(CartDTO::new)
                .collect(Collectors.toList());
        return OK(carts);
    }

    @ApiOperation(value = "장바구니 삭제", notes = "유저의 장바구니 항목 하나를 제거한다.")
    @PostMapping("/t/w/cart/delete/{cart_id}")
    public ApiResult<String> delete(@PathVariable("cart_id") Long cartId){
        cartService.delete(cartId, getAuthUserId());
        return OK("해당 카트를 삭제하였습니다.");
    }

    @ApiOperation(value = "장바구니 담기", notes = "작품을 유저의 장바구니에 담는다.")
    @PostMapping("/t/w/cart-in")
    public ApiResult<CartDTO> cartIn(@RequestBody CartRequest cartRequest){
        return OK(new CartDTO(cartService.save(new Cart(cartRequest), getAuthUserId(), cartRequest.getProductId())));
    }

}
