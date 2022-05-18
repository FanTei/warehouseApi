package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DisplayName("Unit-level testing for ShowcaseItemRepository")
@ActiveProfiles("test")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShowcasesItemRepositoryTest {
    @Autowired
    ShowcaseItemRepository showcaseItemRepository;

    @Sql("/sql/clearDatabase.sql")
    @Test
    public void shouldProperlySaveShowcasesItem() {
        ShowcasesItem showcasesItem = new ShowcasesItem();
        showcasesItem.setQuantity(0);
        showcasesItem.setId(1L);
        showcasesItem.setItem(new Item());
        showcaseItemRepository.save(showcasesItem);
        Optional<ShowcasesItem> saved = showcaseItemRepository.findById(showcasesItem.getId());
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(showcasesItem, saved.get());
    }

    @Sql("/sql/showcasesItems.sql")
    @Test
    public void shouldProperlyFindShowcasesItemByItem() {
        Item mainItem = new Item();
        mainItem.setId(1L);
        mainItem.setTitle("1");
        mainItem.setOccupiedSize(1);
        mainItem.setPrice(1);
        List<ShowcasesItem> find = showcaseItemRepository.findByItem(mainItem);
        Assertions.assertEquals(3,find.size());
    }
}
