package ourdus.ourdusspring.dto.offlineclass.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassSmallCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmallCategoryDTO {

    private Long id;
    private String name;


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

    public SmallCategoryDTO(OfflineClassSmallCategory offlineClassSmallCategory)
    {
        this.id=offlineClassSmallCategory.getId();
        this.name= offlineClassSmallCategory.getName();
    }

}
