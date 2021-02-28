package ourdus.ourdusspring.service.product.option;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.option.ProductChildOption;
import ourdus.ourdusspring.domain.product.option.ProductParentOption;
import ourdus.ourdusspring.dto.product.option.ProductChildOptionRequest;
import ourdus.ourdusspring.repository.product.option.ProductChildOptionRepository;
import ourdus.ourdusspring.repository.product.option.ProductParentOptionRepository;
import ourdus.ourdusspring.repository.product.ProductRepository;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptonService {

    private final ProductParentOptionRepository parentOptionRepository;
    private final ProductChildOptionRepository childOptionRepository;
    private final ProductRepository productRepository;

    public void saveOption(Long productId, List<String> names, LinkedList<ProductChildOptionRequest> childRequests){
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("해당하는 작품을 찾을 수 없습니다."));
        for(int i=1; i<=names.size(); i++){ //parent 옵션을 저장하는 루프
            String name = names.get(i-1);
            ProductParentOption parentOption = ProductParentOption.createBuilder()
                                                .product(product)
                                                .name(name)
                                                .build();
            ProductParentOption saveOption = parentOptionRepository.save(parentOption);
            while(childRequests.size() > 0 && childRequests.peek().getParentId() == i){ //해당번째의 parentId를 가진 child 옵션을 저장하는 루프
                ProductChildOptionRequest childOptionRequest = childRequests.poll();
                ProductChildOption childOption = ProductChildOption.createBuilder()
                        .pOption(saveOption)
                        .childOptionRequest(childOptionRequest)
                        .build();
                childOptionRepository.save(childOption);
            }
        }
    }

    public Map<String, List<?>> findOptions(Long productId){
        Map<String, List<?>> result = new HashMap<>();
        List<ProductParentOption> parentOptions = parentOptionRepository.findAllByProductId(productId);
        List<ProductChildOption> childOptions = childOptionRepository.findAllByProductId(productId);
        result.put("parentOption", parentOptions);
        result.put("childOption", childOptions);
        return result;
    }
}
