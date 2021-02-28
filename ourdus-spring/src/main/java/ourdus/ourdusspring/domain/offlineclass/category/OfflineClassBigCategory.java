package ourdus.ourdusspring.domain.offlineclass.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name="C_BIG_CATEGORY")
public class OfflineClassBigCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BIG_CATEGORY_ID")
    private Long id;

    @Column(name="BIG_CATEGORY_NAME")
    private String name;

    @OneToMany(mappedBy = "offlineClassBigCategory")
    private List<OfflineClassSmallCategory> smallCategoryList = new ArrayList<OfflineClassSmallCategory>();

}
