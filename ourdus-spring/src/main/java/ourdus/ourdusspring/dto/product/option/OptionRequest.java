package ourdus.ourdusspring.dto.product.option;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequest {
    private List<String> names;
    private List<ProductChildOptionRequest> childOptionRequests;
}
