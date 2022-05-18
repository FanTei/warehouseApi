package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.service.ShowcaseService;
import WarehouseAPI.WarehouseAPI.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ShowcaseController.class)
public class ShowcaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private ShowcaseService showcaseService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    @WithMockUser("USER")
    public void whenValidGetShowcases_thenReturns200() throws Exception {
        mockMvc.perform(get("/showcases/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void whenValidAddShowcase_thenReturns200() throws Exception {
        Showcase showcase = new Showcase();
        showcase.setId(1L);
        showcase.setTitle("title");
        showcase.setSize(1);
        mockMvc.perform(post("/showcases/create")
                        .contentType("application/json")
                        .param("showcase", "showcase")
                        .content(objectMapper.writeValueAsString(showcase)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void whenValidAddShowcase_thenReturns403() throws Exception {
        Showcase showcase = new Showcase();
        showcase.setId(1L);
        showcase.setTitle("title");
        showcase.setSize(1);
        mockMvc.perform(post("/showcases/create")
                        .contentType("application/json")
                        .param("showcase", "showcase")
                        .content(objectMapper.writeValueAsString(showcase)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void whenValidDeleteShowcase_thenReturns304() throws Exception {

        mockMvc.perform(delete("/showcases")
                        .param("showcaseId", "1")).andDo(print())
                .andExpect(status().isNotModified());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenValidUpdateShowcase_thenReturns302() throws Exception {
        Showcase showcase = new Showcase();
        showcase.setId(2L);
        showcase.setTitle("title");
        showcase.setSize(1);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("showcaseId", "1");
        requestParams.add("showcase", objectMapper.writeValueAsString(showcase));
        mockMvc.perform(post("/showcases/update")
                        .params(requestParams))
                .andExpect(status().is3xxRedirection());
    }
}
