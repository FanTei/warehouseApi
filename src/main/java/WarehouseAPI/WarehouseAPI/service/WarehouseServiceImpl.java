package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.repository.ItemRepository;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseItemRepository;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseRepository;
import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private ShowcaseRepository showcaseRepository;
    private ItemRepository itemRepository;
    private ShowcaseItemRepository showcaseItemRepository;

    @Autowired
    public WarehouseServiceImpl(ShowcaseRepository showcaseRepository, ItemRepository itemRepository, ShowcaseItemRepository showcaseItemRepository) {
        this.showcaseRepository = showcaseRepository;
        this.itemRepository = itemRepository;
        this.showcaseItemRepository = showcaseItemRepository;
    }

    @Override
    public boolean addItemOnShowcase(Long showcaseId, Long itemId, int quantity) {
        Item addedItem = itemRepository.findById(itemId).get();
        Showcase showcase = showcaseRepository.findById(showcaseId).get();
        if (addedItem != null && showcase != null) {
            List<ShowcasesItem> showcasesItems = showcase.getItems();
            int freePlace = getFreePlace(showcase);
            int itemOccupiedPlace = addedItem.getOccupiedSize() * quantity;
            if (freePlace - itemOccupiedPlace < 0)
                return false;
            ShowcasesItem showcasesItem = new ShowcasesItem(addedItem, quantity);
            showcasesItems.add(showcasesItem);
            showcase.setItems(showcasesItems);
            showcaseRepository.save(showcase);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItemFromShowcase(Long showcaseId, Long itemId) {
        Showcase showcase = showcaseRepository.findById(showcaseId).get();
        if (showcase != null) {
            List<ShowcasesItem> showcasesItems = showcase.getItems();
            if (showcasesItems.size() > 0) {
                ShowcasesItem showcasesItem = showcaseItemRepository.findById(itemId).get();
                if (showcasesItem == null)
                    return false;
                showcasesItems.remove(showcasesItem);
                showcase.setItems(showcasesItems);
                showcaseRepository.save(showcase);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<ShowcasesItem> getShowcasesItems(Long showcaseId) {
        Showcase showcase = showcaseRepository.findById(showcaseId).get();
        return showcase == null
                ? null
                : new ArrayList<>(showcase.getItems());
    }

    private int getOccupiedPlaceOnShowcase(Showcase showcase) {
        int allQuantity = showcase.getItems().stream().mapToInt(ShowcasesItem::getQuantity).sum();
        int allOccupiedSize = showcase.getItems().stream().mapToInt(i -> i.getItem().getOccupiedSize()).sum();
        return allOccupiedSize * allQuantity;
    }

    private int getFreePlace(Showcase showcase) {
        int occupiedPlace = getOccupiedPlaceOnShowcase(showcase);
        int size = showcase.getSize();
        return size - occupiedPlace;
    }
}
