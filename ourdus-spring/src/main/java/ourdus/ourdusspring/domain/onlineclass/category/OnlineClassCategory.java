package ourdus.ourdusspring.domain.onlineclass.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="ONLINE_CLASS_CATEGORY")
public class OnlineClassCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ONLINE_CATEGORY_ID")
    private Long id;

    @Column(name="ONLINE_CATEGORY_NAME")
    private String name;

}
