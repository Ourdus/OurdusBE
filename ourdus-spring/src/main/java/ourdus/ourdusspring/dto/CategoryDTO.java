package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Category;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CategoryDTO {

    private Long id;
    private String name;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public CategoryDTO(Category category)
    {
        this.id=category.getId();
        this.name=category.getName();
        this.image=category.getImage();
    }

}
