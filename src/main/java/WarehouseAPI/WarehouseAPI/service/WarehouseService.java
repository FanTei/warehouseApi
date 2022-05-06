package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;

import java.util.List;

public interface WarehouseService {
    boolean addItemOnShowcase(Long showcaseId,Long itemId,int quantity);

    boolean removeItemFromShowcase(Long showcaseId,Long itemId);

    List<ShowcasesItem> getShowcasesItems(Long showcaseId);
}
