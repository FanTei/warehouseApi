package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findItemById(Long id);
}
