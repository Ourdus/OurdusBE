package ourdus.ourdusspring.controller.offlineclass.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;

import ourdus.ourdusspring.dto.offlineclass.category.BigCategoryDTO;
import ourdus.ourdusspring.service.offlineclass.category.CategoryService;


import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/c")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("category")
    public ApiResult<List<BigCategoryDTO>> viewCategoryList(){
        List<OfflineClassBigCategory> categoryList=categoryService.findAll();
        List<BigCategoryDTO> categoryDTOList=new ArrayList<>();

        categoryList.stream()
                .filter(offlineClassBigCategory -> offlineClassBigCategory != null)
                .forEach(offlineClassBigCategory -> {
                    categoryDTOList.add(new BigCategoryDTO(offlineClassBigCategory));
                });
        return OK(categoryDTOList);
    }

}
