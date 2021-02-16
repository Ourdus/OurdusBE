package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.Promotion;
import ourdus.ourdusspring.domain.PromotionProduct;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.PromotionProductRepository;
import ourdus.ourdusspring.repository.PromotionRepository;

import java.util.NoSuchElementException;

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

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(promotion.getId());
        PromotionProduct pp= PromotionProduct.builder()
                .product(product)
                .promotion(promotion)
                .build();
        promotionProductRepository.save(pp);
        return promotionProduct;
    }

}
