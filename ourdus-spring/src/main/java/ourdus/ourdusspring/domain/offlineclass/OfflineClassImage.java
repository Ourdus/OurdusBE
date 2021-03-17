package ourdus.ourdusspring.domain.offlineclass;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="OFFLINE_CLASS_IMAGE")
@Getter
@NoArgsConstructor
public class OfflineClassImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IMAGE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="CLASS_ID")
    private OfflineClass offlineClass;

    @Column(name="image")
    private String image;

    public void setClass(OfflineClass offlineClass) {
        if(this.offlineClass != null){
            this.offlineClass.getImageList().remove(this);
        }
        this.offlineClass = offlineClass;
        offlineClass.getImageList().add(this);
    }
}
