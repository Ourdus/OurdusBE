package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.option.ProductChildOption;
import ourdus.ourdusspring.domain.product.option.ProductParentOption;
import ourdus.ourdusspring.dto.product.option.ProductChildOptionRequest;
import ourdus.ourdusspring.service.product.option.ProductOptonService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(properties = "spring.profiles.active=dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ProductOptionServiceTest {

    @Autowired
    private ProductOptonService productOptonService;
    private Long productId;
    private List<String> names = new LinkedList<>();
    private LinkedList<ProductChildOptionRequest> childOptionRequests = new LinkedList<>();

    @BeforeAll
    void SetUp(){
        productId = 1L;
        for(Long i=1L; i<5; i++){
            names.add(i+"번째 큰옵션");
            childOptionRequests.add(new ProductChildOptionRequest(i, i+"번째 큰옵션의 작은옵션", Integer.parseInt(i*1000+"")));
        }
    }

    @Test
    void 조회테스트(){
        Map<String, List<?>> result = productOptonService.findOptions(productId);
        List<ProductParentOption> productParentOptions = (List<ProductParentOption>) result.get("parentOption");
        List<ProductChildOption> productChildOptions = (List<ProductChildOption>) result.get("childOption");

        for(ProductParentOption productParentOption : productParentOptions){
            System.out.println(productParentOption);
            assertThat(productParentOption.getProduct().getId(), is(equalTo(productId)));
        }
        for(ProductChildOption productChildOption : productChildOptions){
            System.out.println(productChildOption);
            assertThat(productChildOption.getProductId(), is(equalTo(productId)));
        }
    }

    @Test
    void 저장테스트(){
        productOptonService.saveOption(productId, names, childOptionRequests);
        조회테스트();
    }


}
