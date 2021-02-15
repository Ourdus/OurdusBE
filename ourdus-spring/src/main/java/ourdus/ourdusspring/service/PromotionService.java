package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Promotion;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.PromotionDTO;
import ourdus.ourdusspring.repository.PromotionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService (PromotionRepository promotionRepository)
    {
        this.promotionRepository=promotionRepository;
    }

    public List<Promotion> findAll()
    {
        return  promotionRepository.findAll();
    }

    public Optional<Promotion> findOne(Long promotionID)
    {
        Optional<Promotion> result= promotionRepository.findById(promotionID);
        return result;
    }

    public Promotion save(Promotion promotion)
    {
        promotionRepository.save(promotion);
        return promotion;
    }

    public Promotion update (Long id, Promotion promotion)
    {
        Promotion result=promotionRepository.findById(id).orElseThrow(()-> new NoSuchElementException("프로모션 수정 실패"));
        result.setName(promotion.getName());
        result.setDescription(promotion.getDescription());
        result.setImage(promotion.getImage());
        result.setStart_date(promotion.getStart_date());
        result.setEnd_date(promotion.getEnd_date());
        return result;
    }

    public String deletePromotion (Long promotionId)
    {
        if(!promotionRepository.existsById(promotionId))
            new NoSuchElementException("프로모션 삭제 실패");
        promotionRepository.deleteById(promotionId);
        return "프로모션 삭제 성공";
    }
}
