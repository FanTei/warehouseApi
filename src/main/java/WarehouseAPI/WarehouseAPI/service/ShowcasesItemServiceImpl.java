package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseItemRepository;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowcasesItemServiceImpl implements ShowcasesItemService {
    private final ShowcaseItemRepository showcaseItemRepository;

    public ShowcasesItemServiceImpl(ShowcaseItemRepository showcaseItemRepository) {
        this.showcaseItemRepository = showcaseItemRepository;
    }
    @Override
    public void deleteShowcasesItemsByDependItems(List<ShowcasesItem> showcasesItems) {
        showcaseItemRepository.deleteAll(showcasesItems);
    }

    @Override
    public List<ShowcasesItem> getShowcasesItemsByDependItem(Item item) {
        return showcaseItemRepository.findByItem(item);
    }
}
