package ourdus.ourdusspring.service.offlineclass.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;
import ourdus.ourdusspring.repository.offlineclass.category.OfflineClassBigCategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private OfflineClassBigCategoryRepository offlineClassBigCategoryRepository;

    public CategoryService(OfflineClassBigCategoryRepository offlineClassBigCategoryRepository)
    {
        this.offlineClassBigCategoryRepository=offlineClassBigCategoryRepository;
    }

    public List<OfflineClassBigCategory> findAll()
    {
        return offlineClassBigCategoryRepository.findAll();
    }
}
