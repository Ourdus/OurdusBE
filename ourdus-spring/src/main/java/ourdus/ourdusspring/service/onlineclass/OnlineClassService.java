package ourdus.ourdusspring.service.onlineclass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;
import ourdus.ourdusspring.domain.onlineclass.category.OnlineClassCategory;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;
import ourdus.ourdusspring.repository.onlineclass.OnlineClassRepository;
import ourdus.ourdusspring.repository.onlineclass.category.OnlineClassCategoryRepository;
import ourdus.ourdusspring.repository.onlineclass.comment.OnlineClassCommentRepository;
import ourdus.ourdusspring.service.user.UserService;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class OnlineClassService {

    private final OnlineClassRepository onlineClassRepository;
    private final OnlineClassCategoryRepository onlineClassCategoryRepository;
    private final UserService userService;
    private final OnlineClassCommentRepository onlineClassCommentRepository;

    public OnlineClass save(Long categoryId, Long authorId, OnlineClass onlineClass){
        checkNotNull(categoryId);
        checkNotNull(authorId);
        checkNotNull(onlineClass);
        onlineClass.setCategory(findCategoryById(categoryId));
        onlineClass.setAuthor(userService.findUser(authorId));
        return onlineClassRepository.save(onlineClass);
    }
    public void delete(Long onlineClassId, Long userId){
        checkNotNull(onlineClassId);
        checkNotNull(userId);
        OnlineClass onlineClass = findOne(onlineClassId);
        onlineClass.validOwner(userId);
        onlineClassRepository.delete(onlineClass);
    }

    public OnlineClass update(Long categoryId, Long onlineClassId, OnlineClass onlineClass, Long authorId){
        OnlineClass before = findOne(onlineClassId);
        before.validOwner(authorId);
        if(categoryId != before.getCategory().getId()){
            OnlineClassCategory category = findCategoryById(onlineClassId);
            before.setCategory(category);
        }

        before.change(onlineClass);
        return before;
    }

    public OnlineClassComment addComment(OnlineClassComment comment, Long onlineClassId, Long userId) {
        comment.setOnlineClass(findOne(onlineClassId));
        comment.setUser(userService.findUser(userId));
        onlineClassCommentRepository.save(comment);
        return comment;
    }

    public String removeComment(Long commentId, Long userId) {
        OnlineClassComment comment = findCommentById(commentId);
        comment.validOwner(userId);
        onlineClassCommentRepository.delete(comment);
        return "comment delete success";
    }

    @Transactional(readOnly = true)
    public List<OnlineClass> findAll(){
        return onlineClassRepository.findAll();
    }

    @Transactional(readOnly = true)
    public OnlineClass findOne(Long onlineClassId){
        return onlineClassRepository.findById(onlineClassId)
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 온라인 클래스가 없습니다."));
    }

    @Transactional(readOnly = true)
    public OnlineClassCategory findCategoryById(Long onlineClassCategoryId){
        return onlineClassCategoryRepository.findById(onlineClassCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("찾으려는 온라인 클래스 카테고리가 없습니다."));
    }

    @Transactional(readOnly = true)
    public OnlineClassComment findCommentById(Long onlineClassCommentId) {
        return onlineClassCommentRepository.findById(onlineClassCommentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 온라인 클래스 댓글 정보가 없습니다."));
    }
}
