package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Item;

import java.util.List;


public interface ItemService {
    void create(Item item);

    List<Item> readAll();

    Item read(Long id);

    boolean update(Item item, Long id);

    boolean delete(Long id);
}
