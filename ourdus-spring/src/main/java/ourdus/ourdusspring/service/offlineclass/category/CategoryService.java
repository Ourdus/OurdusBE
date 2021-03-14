package ourdus.ourdusspring.service.offlineclass.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassSmallCategory;
import ourdus.ourdusspring.repository.offlineclass.category.OfflineClassBigCategoryRepository;
import ourdus.ourdusspring.repository.offlineclass.category.OfflineClassSmallCategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private OfflineClassBigCategoryRepository offlineClassBigCategoryRepository;
    private OfflineClassSmallCategoryRepository offlineClassSmallCategoryRepository;

    public CategoryService(OfflineClassBigCategoryRepository offlineClassBigCategoryRepository,OfflineClassSmallCategoryRepository offlineClassSmallCategoryRepository)
    {
        this.offlineClassBigCategoryRepository=offlineClassBigCategoryRepository;
        this.offlineClassSmallCategoryRepository=offlineClassSmallCategoryRepository;
    }

    public List<OfflineClassBigCategory> findAll()
    {
        return offlineClassBigCategoryRepository.findAll();
    }

    public OfflineClassSmallCategory findOne(Long categoryId)
    {
        return offlineClassSmallCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new NoSuchElementException("해당 카테고리가 존재하지 않습니다"));
    }
}
