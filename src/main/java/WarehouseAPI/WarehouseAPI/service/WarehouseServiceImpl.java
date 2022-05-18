package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.repository.ItemRepository;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseItemRepository;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final ShowcaseRepository showcaseRepository;
    private final ItemRepository itemRepository;
    private final ShowcaseItemRepository showcaseItemRepository;

    public WarehouseServiceImpl(ShowcaseRepository showcaseRepository, ItemRepository itemRepository, ShowcaseItemRepository showcaseItemRepository) {
        this.showcaseRepository = showcaseRepository;
        this.itemRepository = itemRepository;
        this.showcaseItemRepository = showcaseItemRepository;
    }


    @Override
    public boolean addItemOnShowcase(Long showcaseId, Long itemId, int quantity) {
        Item addedItem = itemRepository.findById(itemId).orElse(null);
        Showcase showcase = showcaseRepository.findById(showcaseId).orElse(null);
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
        Showcase showcase = showcaseRepository.findById(showcaseId).orElse(null);
        if (showcase != null) {
            List<ShowcasesItem> showcasesItems = showcase.getItems();
            if (showcasesItems.size() > 0) {
                ShowcasesItem showcasesItem = showcaseItemRepository.findById(itemId).orElse(null);
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
        Showcase showcase = showcaseRepository.findById(showcaseId).orElse(null);
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
