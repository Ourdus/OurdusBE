package ourdus.ourdusspring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="PRODUCT")
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CART_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    private User author;

    @Column(name="OPTION_INFO")
    private String optionInfo;
    @Column(name="product_num")
    private int productNum;

    @Column(name = "CART_IN_DATE")
    @CreationTimestamp
    private LocalDateTime cartDate;


}
