package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@DisplayName("Unit-level testing for ShowcaseRepository")
@ActiveProfiles("test")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShowcaseRepositoryTest {
    @Autowired
    ShowcaseRepository showcaseRepository;

    @Sql("/sql/clearDatabase.sql")
    @Test
    public void shouldProperlySaveShowcase() {
        Showcase showcase = new Showcase();
        showcase.setTitle("test");
        showcase.setSize(0);
        showcase.setId(1L);
        showcaseRepository.save(showcase);
        Optional<Showcase> saved = showcaseRepository.findById(showcase.getId());
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(showcase, saved.get());
    }

    @Sql("/sql/showcases.sql")
    @Test
    public void shouldProperlyFindShowcase(){
        long id = 1L;
        Optional<Showcase> find = showcaseRepository.findById(id);
        Assertions.assertTrue(find.isPresent());
        Assertions.assertEquals(find.get().getTitle(),"1");
    }
}
