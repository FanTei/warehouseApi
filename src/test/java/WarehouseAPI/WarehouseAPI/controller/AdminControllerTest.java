package WarehouseAPI.WarehouseAPI.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenValidDeleteUser_thenReturns200() throws Exception {
        mockMvc.perform(post("/admin/delete")
                        .param("userId", "1"))
                .andExpect(status().isNotModified());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenValidSetRoleUser_thenReturns200() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("userId", "1");
        requestParams.add("roleId", "1");
        mockMvc.perform(post("/admin/setRole/")
                        .params(requestParams))
                .andExpect(status().isOk());
    }
}
