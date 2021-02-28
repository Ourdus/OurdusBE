package ourdus.ourdusspring.service.offlineclass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassSmallCategory;
import ourdus.ourdusspring.domain.offlineclass.comment.OfflineClassComment;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.repository.offlineclass.OfflineClassRepository;
import ourdus.ourdusspring.repository.offlineclass.category.OfflineClassSmallCategoryRepository;
import ourdus.ourdusspring.repository.offlineclass.comment.OfflineClassCommentRepository;
import ourdus.ourdusspring.repository.offlineclass.review.OfflineClassReviewRepository;
import ourdus.ourdusspring.repository.user.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OfflineClassService {

    private final OfflineClassRepository offlineClassRepository;
    private final UserRepository userRepository;
    private final OfflineClassSmallCategoryRepository offlineClassSmallCategory;
    private final OfflineClassCommentRepository offlineClassCommentRepository;
    private final OfflineClassReviewRepository offlineClassReviewRepository;

    public List<OfflineClass> findAll() {return offlineClassRepository.findAll();}

    public OfflineClass findOne(Long offlineClassId) {
        return offlineClassRepository.findById(offlineClassId).orElseThrow(() -> new NoSuchElementException("찾으려는 온라인 클래스가 없습니다."));
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

    public OfflineClassComment addComment(OfflineClassComment comment, Long classId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("해당하는 유저가 없습니다."));
        OfflineClass offlineClass = offlineClassRepository.findById(classId)
                .orElseThrow(()->new NoSuchElementException("해당하는 클래스가 없습니다."));
        comment.setOfflineClass(offlineClass);
        comment.setUser(user);
        offlineClassCommentRepository.save(comment);
        return comment;
    }

    public String removeComment(Long commentId) {
        offlineClassCommentRepository.deleteById(commentId);
        return "comment delete success";
    }

}
