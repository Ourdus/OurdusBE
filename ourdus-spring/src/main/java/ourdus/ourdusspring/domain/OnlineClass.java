package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.dto.OnlineClassDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public OnlineClass(OnlineClass createOnlineClass, User author, OnlineClassCategory category) {
        this.name = createOnlineClass.getName();
        this.price = createOnlineClass.getPrice();
        this.description = createOnlineClass.getDescription();
        this.duration = createOnlineClass.getDuration();
        this.level = createOnlineClass.getLevel();
        this.startDate = createOnlineClass.getStartDate();
        this.prepareFlag = createOnlineClass.isPrepareFlag();
        this.image = createOnlineClass.getImage();
        this.author = author;
        this.category = category;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("price", price)
                .append("description", description)
                .append("duration", duration)
                .append("level", level)
                .append("startDate", startDate)
                .append("prepareFlag", prepareFlag)
                .append("hit", hit)
                .append("purchase", purchase)
                .append("like", like)
                .append("rate", rate)
                .append("image", image)
                .append("author", author.getId())
                .append("category", category.getId())
                .toString();
    }
}


