package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.repository.ItemRepository;
import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShowcasesItemService showcasesItemService;

    @Override
    public void create(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> readAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item read(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Item item, Long id) {
        Item itemToUpdate = itemRepository.findById(id).orElse(null);
        if (itemToUpdate != null) {
            itemToUpdate.setTitle(item.getTitle());
            itemToUpdate.setOccupiedSize(item.getOccupiedSize());
            itemToUpdate.setPrice(item.getPrice());
            itemRepository.save(itemToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Item itemToDelete = itemRepository.findById(id).orElse(null);
        if (itemToDelete != null) {
            List<ShowcasesItem> dependItems = showcasesItemService.getShowcasesItemsByDependItem(itemToDelete);
            showcasesItemService.deleteShowcasesItemsByDependItems(dependItems);

            itemRepository.delete(itemToDelete);
            return true;
        }
        return false;
    }
}
