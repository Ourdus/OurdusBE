package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.controller.OfflineClassController;
import ourdus.ourdusspring.domain.*;
import ourdus.ourdusspring.repository.OfflineClassRepository;
import ourdus.ourdusspring.repository.OfflineClassSmallCategoryRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class OfflineClassService {

    private final OfflineClassRepository offlineClassRepository;
    private final UserRepository userRepository;
    private final OfflineClassSmallCategoryRepository offlineClassSmallCategory;


    public OfflineClassService (OfflineClassRepository offlineClassRepository,UserRepository userRepository,OfflineClassSmallCategoryRepository offlineClassSmallCategory)
    {
        this.offlineClassRepository=offlineClassRepository;
        this.userRepository = userRepository;
        this.offlineClassSmallCategory=offlineClassSmallCategory;
    }

    public List<OfflineClass> findAll() {return offlineClassRepository.findAll();}

    public Optional<OfflineClass> findOne(Long classID)
    {
        Optional<OfflineClass> result= offlineClassRepository.findById(classID);
        return result;
    }

    public OfflineClass save(OfflineClass offlineClass,Long categoryId, Long authorId)
    {
        User author=userRepository.findById(authorId).orElseThrow(()->new NoSuchElementException("해당하는 아이디를 찾을 수 없습니다"));
        OfflineClassSmallCategory ocsc=offlineClassSmallCategory.findById(categoryId).orElseThrow(()->new NoSuchElementException("해당 카테고리 아이디를 찾을 수 없습니다"));

        OfflineClass olc= OfflineClass.builder()
                .name(offlineClass.getName())
                .description(offlineClass.getDescription())
                .price(offlineClass.getPrice())
                .duration(offlineClass.getDuration())
                .hit(offlineClass.getHit())
                .max(offlineClass.getMax())
                .level(offlineClass.getLevel())
                .rate(offlineClass.getRate())
                .like(offlineClass.getLike())
                .place(offlineClass.getPlace())
                .purchase(offlineClass.getPurchase())
                .price(offlineClass.getPrice())
                .author(author)
                .offlineClassSmallCategory(ocsc)
                .build();
        OfflineClass result= offlineClassRepository.save(olc);
        return result;
    }

    public String delete(Long classId) {
        if(!offlineClassRepository.existsById(classId))
            new NoSuchElementException("오프라인 클래스 삭제 실패");
        offlineClassRepository.deleteById(classId);
        return "오프라인 클래스 삭제 성공";
    }

    public OfflineClass update(OfflineClass offlineClass, Long classId) {
        OfflineClass result = offlineClassRepository.findById(classId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 오프라인 클래스는 수정할 수 없습니다."));
        result.setDescription(offlineClass.getDescription());
        result.setPrice(offlineClass.getPrice());
        result.setName(offlineClass.getName());
        result.setHit(offlineClass.getHit());
        result.setDuration(offlineClass.getDuration());
        result.setLevel(offlineClass.getLevel());
        result.setLike(offlineClass.getLike());
        result.setPlace(offlineClass.getPlace());
        result.setMax(offlineClass.getMax());
        result.setPurchase(offlineClass.getPurchase());
        return result;
    }

}
