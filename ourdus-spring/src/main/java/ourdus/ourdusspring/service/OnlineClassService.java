package ourdus.ourdusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.OnlineClass;
import ourdus.ourdusspring.domain.OnlineClassCategory;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.repository.OnlineClassCategoryRepository;
import ourdus.ourdusspring.repository.OnlineClassRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OnlineClassService {

    private final OnlineClassRepository onlineClassRepository;
    private final OnlineClassCategoryRepository onlineClassCategoryRepository;
    private final UserRepository userRepository;

    public List<OnlineClass> findall(){
        return onlineClassRepository.findAll();
    }

    public OnlineClass findOne(Long onlineClassId){
        return onlineClassRepository.findById(onlineClassId).orElseThrow(() -> new NoSuchElementException("찾으려는 온라인 클래스가 없습니다."));
    }

    public List<OnlineClass> findByCategory(Long CategoryId){
        return onlineClassRepository.findByCategoryId(CategoryId);
    }

    public OnlineClass save(Long categoryId, Long authorId, OnlineClass onlineClass){
        OnlineClassCategory category = onlineClassCategoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("찾을 수 없는 카테고리입니다."));
        User author = userRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("찾을 수 없는 작가입니다."));
        OnlineClass saveClass = OnlineClass.defaultBuilder()
                .category(category)
                .author(author)
                .createOnlineClass(onlineClass)
                .build();
        onlineClassRepository.save(saveClass);
        return saveClass;
    }
    public void delete(Long onlineClassId){
        onlineClassRepository.deleteById(onlineClassId);
    }

    public OnlineClass update(Long categoryId, OnlineClass onlineClass){
        OnlineClass result = onlineClassRepository.findById(onlineClass.getId()).orElseThrow(() -> new NoSuchElementException("잘못된 온라인 클래스 아이디입니다."));
        if(categoryId != result.getCategory().getId()){//
            OnlineClassCategory category = onlineClassCategoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("수정하려는 카테고리는 찾을 수 없는 카테고리입니다."));
            result.setCategory(category);
        }
        result.setName(onlineClass.getName());
        result.setPrice(onlineClass.getPrice());
        result.setDescription(onlineClass.getDescription());
        result.setDuration(onlineClass.getDuration());
        result.setLevel(onlineClass.getLevel());
        result.setStartDate(onlineClass.getStartDate());
        return result;
    }

}
