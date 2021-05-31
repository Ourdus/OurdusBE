package ourdus.ourdusspring.domain.onlineclass;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import ourdus.ourdusspring.domain.onlineclass.category.OnlineClassCategory;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;
import ourdus.ourdusspring.domain.onlineclass.review.OnlineClassReview;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.onlineclass.OnlineClassDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.util.CompareValueUtils.changeValue;

@Entity
@NoArgsConstructor
@Getter
@Table(name="ONLINE_CLASS")
public class OnlineClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ONLINE_CLASS_ID")
    private Long id;

    @Column(name="ONLINE_CLASS_NAME")
    private String name;

    @Column(name="ONLINE_CLASS_PRICE")
    private int price;

    @Column(name="ONLINE_CLASS_DESCRIPTION")
    private String description;

    @Column(name="ONLINE_CLASS_DURATION")
    private int duration;

    @Column(name="ONLINE_CLASS_LEVEL")
    private String level;

    @Column(name="ONLINE_CLASS_START_DATE")
    private String startDate;

    @Column(name="PREPARATION_FLAG")
    @ColumnDefault("false")
    private boolean prepareFlag;

    @Column(name="ONLINE_CLASS_HIT")
    @ColumnDefault("0")
    private int hit;

    @Column(name="ONLINE_CLASS_PURCHASE")
    @ColumnDefault("0")
    private int purchase;

    @Column(name="ONLINE_CLASS_LIKE")
    @ColumnDefault("0")
    private int like;

    @Column(name="ONLINE_CLASS_RATE")
    @ColumnDefault("0")
    private int rate;

    @Column(name="ONLINE_CLASS_IMAGE")
    private String image;

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    private User author;

    @ManyToOne
    @JoinColumn(name="ONLINE_CATEGORY_ID")
    private OnlineClassCategory category;

    @OneToMany(mappedBy = "onlineClass", cascade = CascadeType.ALL)
    private List<OnlineClassComment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "onlineClass", cascade = CascadeType.PERSIST)
    private List<OnlineClassReview> reviews = new ArrayList<>();

    //연관관계메소드
    public void addReview(OnlineClassReview review){
        reviews.add(review);
        review.setOnlineClass(this);
    }

    //연관관계 메서드
    public void addComment(OnlineClassComment comment){
        commentList.add(comment);
        comment.setOnlineClass(this);
    }

    public void changePrepareFlag(boolean prepareFlag) {
        this.prepareFlag = prepareFlag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCategory(OnlineClassCategory category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void validOwner(Long authorId) {
        if(!this.author.isUser(authorId)) {
            throw new IllegalStateException("해당 온라인 클래스의 작가가 아닙니다.");
        }
    }

    public void change(OnlineClass onlineClass) {
        this.name = changeValue(this.name, onlineClass.name);
        this.price = changeValue(this.price, onlineClass.price);
        this.description = changeValue(this.description, onlineClass.description);
        this.duration = changeValue(this.duration, onlineClass.duration);
        this.level = changeValue(this.level, onlineClass.level);
        this.startDate = changeValue(this.startDate, onlineClass.startDate);
        this.image = changeValue(this.image, onlineClass.image);
    }

    @Builder
    public OnlineClass(OnlineClassDTO onlineClassDTO) {
        this.name = onlineClassDTO.getName();
        this.price = onlineClassDTO.getPrice();
        this.description = onlineClassDTO.getDescription();
        this.duration = onlineClassDTO.getDuration();
        this.level = onlineClassDTO.getLevel();
        this.startDate = onlineClassDTO.getStartDate();
        this.prepareFlag = onlineClassDTO.isPrepareFlag();
        this.image = onlineClassDTO.getImage();
    }


}


