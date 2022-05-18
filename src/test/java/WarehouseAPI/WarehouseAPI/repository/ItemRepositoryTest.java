package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@DisplayName("Unit-level testing for ItemRepository")
@ActiveProfiles("test")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Sql("/sql/clearDatabase.sql")
    @Test
    public void shouldProperlySaveItem() {
        Item item = new Item();
        item.setPrice(0);
        item.setTitle("test");
        item.setPrice(0);
        item.setId(1L);
        itemRepository.save(item);
        Optional<Item> saved = itemRepository.findById(item.getId());
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(item, saved.get());
    }

    @Sql("/sql/items.sql")
    @Test
    public void shouldProperlyFindItemById() {
        long id = 1L;
        Optional<Item> find = itemRepository.findById(id);
        Assertions.assertTrue(find.isPresent());
        Assertions.assertEquals(find.get().getTitle(), "1");
    }
}
