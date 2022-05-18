package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Item;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for ItemService")
public class ItemServiceTest {
    private final ItemServiceImpl itemService = Mockito.mock(ItemServiceImpl.class);

    @Test
    public void shouldProperlyUpdateItem() {
        Item item = new Item();
        item.setTitle("update");
        item.setPrice(123);
        item.setOccupiedSize(123);
        item.setId(1L);
        itemService.update(item, 0L);
        Mockito.verify(itemService).update(item, 0L);
    }

    @Test
    public void shouldProperlyDeleteItem() {
        long id = 0L;
        itemService.delete(id);
        Mockito.verify(itemService).delete(id);
    }
}
