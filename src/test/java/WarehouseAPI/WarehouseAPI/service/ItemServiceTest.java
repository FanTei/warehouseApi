package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@DisplayName("Unit-level testing for ItemService")
public class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Sql("/sql/items.sql")

}
