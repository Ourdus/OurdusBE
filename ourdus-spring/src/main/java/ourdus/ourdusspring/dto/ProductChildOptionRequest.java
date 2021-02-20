package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductChildOptionRequest {
    private Long parentId;
    private String name;
    private int price;
}
