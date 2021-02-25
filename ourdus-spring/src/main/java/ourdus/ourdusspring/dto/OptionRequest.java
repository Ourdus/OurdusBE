package ourdus.ourdusspring.dto;


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