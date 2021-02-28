package ourdus.ourdusspring.domain.offlineclass.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name="C_SMALL_CATEGORY")
public class OfflineClassSmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SMALL_CATEGORY_ID")
    private Long id;

    @Column(name="SMALL_CATEGORY_NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="BIG_CATEGORY_ID")
    private OfflineClassBigCategory offlineClassBigCategory;

    @OneToMany(mappedBy = "offlineClassSmallCategory")
    private List<OfflineClass> offlineClassesList= new ArrayList<>();

}
