package ourdus.ourdusspring.dto.offlineclass.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassSmallCategory;
import ourdus.ourdusspring.dto.product.review.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BigCategoryDTO {
    private Long id;
    private String name;
    private List<SmallCategoryDTO> categorySmallList= new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SmallCategoryDTO> getCategorySmallList() {
        return categorySmallList;
    }

    public void setCategorySmallList(List<SmallCategoryDTO> categorySmallList) {
        this.categorySmallList = categorySmallList;
    }

    public void addSmallCategory(OfflineClassSmallCategory offlineClassSmallCategory){
        this.categorySmallList.add(new SmallCategoryDTO(offlineClassSmallCategory));
    }

    public BigCategoryDTO (OfflineClassBigCategory offlineClassBigCategory)
    {
        this.id=offlineClassBigCategory.getId();
        this.name= offlineClassBigCategory.getName();
        for(OfflineClassSmallCategory offlineClassSmallCategory: offlineClassBigCategory.getSmallCategoryList()){
            this.addSmallCategory(offlineClassSmallCategory);
        }
    }
}
