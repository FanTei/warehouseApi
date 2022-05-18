package WarehouseAPI.WarehouseAPI.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for WarehouseService")
public class WarehouseServiceTest {
    private final WarehouseServiceImpl warehouseService = Mockito.mock(WarehouseServiceImpl.class);

    @Test
    public void shouldProperlyAddItemOnShowcase() {
        Long showcaseId = 1L;
        Long itemId = 1L;
        int quantity = 0;
        warehouseService.addItemOnShowcase(showcaseId, itemId, quantity);
        Mockito.verify(warehouseService).addItemOnShowcase(showcaseId, itemId, quantity);
    }

    @Test
    public void shouldProperlyRemoveItemFromShowcase() {
        Long showcaseId = 1L;
        Long itemId = 1L;
        warehouseService.removeItemFromShowcase(showcaseId, itemId);
        Mockito.verify(warehouseService).removeItemFromShowcase(showcaseId, itemId);
    }
}
