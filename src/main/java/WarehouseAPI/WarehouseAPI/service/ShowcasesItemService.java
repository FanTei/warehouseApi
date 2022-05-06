package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ShowcasesItemService {
    void deleteShowcasesItemsByDependItems(List<ShowcasesItem> showcasesItems);

    List<ShowcasesItem> getShowcasesItemsByDependItem(Item item);
}
