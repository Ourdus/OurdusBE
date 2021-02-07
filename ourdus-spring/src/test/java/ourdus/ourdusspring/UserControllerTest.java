package ourdus.ourdusspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    public void 삭제테스트(){
     /*   mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete"))
                .andExpect(MockMvcRe);*/


    }

}
