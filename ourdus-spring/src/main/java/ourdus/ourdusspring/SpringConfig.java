//package ourdus.ourdusspring;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ourdus.ourdusspring.repository.user.AddressRepository;
//import ourdus.ourdusspring.repository.product.category.CategoryRepository;
//import ourdus.ourdusspring.repository.product.ProductRepository;
//import ourdus.ourdusspring.repository.user.UserRepository;
//import ourdus.ourdusspring.service.user.UserService;
//
//@Configuration
//public class SpringConfig {
//
//    private final UserRepository userRepository;
//
//    private final AddressRepository addressRepository;
//
//    private final CategoryRepository categoryRepository;
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public SpringConfig(UserRepository userRepository, AddressRepository addressRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
//        this.userRepository = userRepository;
//        this.addressRepository = addressRepository;
//        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
//    }
//
////    @Autowired
////    public SpringConfig(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
//    @Bean
//    public UserService userService() {
//        return new UserService(userRepository);
//    }
//
//}
