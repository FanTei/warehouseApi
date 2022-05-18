package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.service.ItemService;
import WarehouseAPI.WarehouseAPI.service.UserService;
import WarehouseAPI.WarehouseAPI.service.WarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WarehouseController.class)
public class WarehouseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @MockBean
    private WarehouseService warehouseService;

    @MockBean
    ItemService itemService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void whenValidGetShowcasesItems_thenReturns304() throws Exception {
        mockMvc.perform(get("/warehouse/getItems")
                        .param("showcaseId", "1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void whenValidAddItemOnShowcase_thenReturns304() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("showcaseId", "1");
        requestParams.add("itemId", "1");
        requestParams.add("quantity", "1");
        mockMvc.perform(post("/warehouse/add")
                        .params(requestParams))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void henValidDeleteItemFromShowcase_thenReturns304() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("showcaseId", "1");
        requestParams.add("itemId", "1");
        mockMvc.perform(delete("/warehouse/add")
                        .params(requestParams))
                .andExpect(status().is3xxRedirection());
    }
}
