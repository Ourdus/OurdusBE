package ourdus.ourdusspring.controller.offlineclass.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;

import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassSmallCategory;
import ourdus.ourdusspring.dto.offlineclass.OfflineClassDTO;
import ourdus.ourdusspring.dto.offlineclass.OfflineClassRequest;
import ourdus.ourdusspring.dto.offlineclass.category.BigCategoryDTO;
import ourdus.ourdusspring.dto.offlineclass.category.SmallCategoryDTO;
import ourdus.ourdusspring.service.offlineclass.OfflineClassService;
import ourdus.ourdusspring.service.offlineclass.category.CategoryService;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/c")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private OfflineClassService offlineClassService;

    @GetMapping("category")
    public ApiResult<List<BigCategoryDTO>> viewCategoryOCList(){
        List<OfflineClassBigCategory> categoryList=categoryService.findAll();
        List<BigCategoryDTO> categoryDTOList=new ArrayList<>();

        categoryList.stream()
                .filter(offlineClassBigCategory -> offlineClassBigCategory != null)
                .forEach(offlineClassBigCategory -> {
                    categoryDTOList.add(new BigCategoryDTO(offlineClassBigCategory));
                });
        return OK(categoryDTOList);
    }

    @GetMapping("small/{category_id}")
    public ApiResult<SmallCategoryDTO> viewSmallCategoryOCList(@PathVariable("category_id") Long categoryId){
        return OK(new SmallCategoryDTO(categoryService.findOne(categoryId)));
    }

}
