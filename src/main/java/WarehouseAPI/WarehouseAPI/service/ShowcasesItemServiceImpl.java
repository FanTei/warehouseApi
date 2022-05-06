package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowcasesItemServiceImpl implements ShowcasesItemService {
    @Autowired
    ShowcaseItemRepository showcaseItemRepository;

    @Override
    public void deleteShowcasesItemsByDependItems(List<ShowcasesItem> showcasesItems) {
        showcaseItemRepository.deleteAll(showcasesItems);
    }

    @Override
    public List<ShowcasesItem> getShowcasesItemsByDependItem(Item item) {
        return showcaseItemRepository.findByItem(item);
    }
}
