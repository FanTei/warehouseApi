package WarehouseAPI.WarehouseAPI.repository;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShowcaseItemRepository extends JpaRepository<ShowcasesItem, Long> {

    List<ShowcasesItem> findByItem(Item item);

}
