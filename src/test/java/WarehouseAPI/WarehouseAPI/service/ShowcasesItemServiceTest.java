package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Unit-level testing for ShowcasesItemService")
public class ShowcasesItemServiceTest {
    private final ShowcasesItemServiceImpl showcasesItemService = Mockito.mock(ShowcasesItemServiceImpl.class);

    @Test
    public void shouldProperlyDeleteShowcasesItem() {
        List<ShowcasesItem> showcasesItems = new ArrayList<>();
        showcasesItemService.deleteShowcasesItemsByDependItems(showcasesItems);
        Mockito.verify(showcasesItemService).deleteShowcasesItemsByDependItems(showcasesItems);
    }


}
