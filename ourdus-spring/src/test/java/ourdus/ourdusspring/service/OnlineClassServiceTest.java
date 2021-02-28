package ourdus.ourdusspring.service;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.service.onlineclass.OnlineClassService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class OnlineClassServiceTest {

    @Autowired
    private OnlineClassService onlineClassService;



}
