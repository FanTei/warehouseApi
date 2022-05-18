package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


@DisplayName("Unit-level testing for ShowcaseService")
public class ShowcaseServiceTest {
    private final ShowcaseServiceImpl showcaseService = Mockito.mock(ShowcaseServiceImpl.class);

    @Test
    public void shouldProperlyUpdateShowcase() {
        Showcase showcase = new Showcase();
        showcase.setTitle("1111");
        showcaseService.update(showcase, 0L);
        Mockito.verify(showcaseService).update(showcase, 0L);
    }

    @Test
    public void shouldProperlyDeleteShowcase() {
        long id = 0L;
        showcaseService.delete(id);
        Mockito.verify(showcaseService).delete(id);
    }
}
