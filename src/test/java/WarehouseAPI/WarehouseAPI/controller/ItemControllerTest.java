package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.service.ItemService;
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
@WebMvcTest(controllers = ItemController.class)
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private ItemService itemService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @WithMockUser("USER")
    public void whenValidGetItems_thenReturns200() throws Exception {
        mockMvc.perform(get("/items/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void whenValidAddItem_thenReturns200() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("test");
        item.setOccupiedSize(1);
        item.setPrice(1);
        mockMvc.perform(post("/items/create")
                        .param("item", "item")
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void whenValidAddItem_thenReturns403() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("test");
        item.setOccupiedSize(1);
        item.setPrice(1);
        mockMvc.perform(post("/items/create")
                        .param("item", "item")
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void whenValidDeleteItem_thenReturns304() throws Exception {
        mockMvc.perform(delete("/items")
                        .param("id", "1")).andDo(print())
                .andExpect(status().isNotModified());
        ;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenValidUpdateShowcase_thenReturns302() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("test");
        item.setOccupiedSize(1);
        item.setPrice(1);
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("itemId", "1");
        requestParams.add("item", objectMapper.writeValueAsString(item));
        mockMvc.perform(post("/items/update")
                        .params(requestParams))
                .andExpect(status().isOk());
    }
}
