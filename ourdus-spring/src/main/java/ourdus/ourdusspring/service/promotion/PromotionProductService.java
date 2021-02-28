package ourdus.ourdusspring.service.promotion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.promotion.Promotion;
import ourdus.ourdusspring.domain.promotion.PromotionProduct;
import ourdus.ourdusspring.repository.product.ProductRepository;
import ourdus.ourdusspring.repository.promotion.PromotionProductRepository;
import ourdus.ourdusspring.repository.promotion.PromotionRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PromotionProductService {

    private final PromotionProductRepository promotionProductRepository;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    public PromotionProductService (PromotionProductRepository promotionProductRepository,ProductRepository productRepository,PromotionRepository promotionRepository)
    {
        this.promotionProductRepository=promotionProductRepository;
        this.productRepository = productRepository;
        this.promotionRepository=promotionRepository;
    }

    public PromotionProduct save(PromotionProduct promotionProduct, Long promotionId, Long productId)
    {

        Product product = productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException("해당 작품이 존재하지 않습니다"));
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(()->new NoSuchElementException("해당 번호의 프로모션이 존재하지 않습니다"));

        PromotionProduct pp= PromotionProduct.builder()
                .product(product)
                .promotion(promotion)
                .build();
        promotionProductRepository.save(pp);
        return promotionProduct;
    }

    public String delete(Long promotionId) {

        Optional<PromotionProduct> findPP =promotionProductRepository.findById(promotionId);
        if(findPP.isPresent()){
            promotionProductRepository.deleteById(promotionId);
            return "프로모션 작품 삭제 성공";
        }else{
            throw new NoSuchElementException("존재하지 않는 작품은 삭제할 수 없습니다.");
        }
    }

}
