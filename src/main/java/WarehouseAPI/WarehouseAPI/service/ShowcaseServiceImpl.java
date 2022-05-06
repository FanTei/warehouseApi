package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.repository.ShowcaseRepository;
import WarehouseAPI.WarehouseAPI.entity.Showcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowcaseServiceImpl implements ShowcaseService {
    private final ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseServiceImpl(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }

    @Override
    public void create(Showcase showcase) {
        showcaseRepository.save(showcase);
    }

    @Override
    public List<Showcase> readAll() {
        return new ArrayList<>(showcaseRepository.findAll());
    }

    @Override
    public Showcase read(Long id) {
        return showcaseRepository.findById(id).get();
    }

    @Override
    public boolean update(Showcase showcase, Long id) {
        Showcase showcaseToUpdate = showcaseRepository.findById(id).get();
        if (showcaseToUpdate != null) {
            showcaseToUpdate.setItems(showcase.getItems());
            showcaseToUpdate.setTitle(showcase.getTitle());
            showcaseToUpdate.setSize(showcase.getSize());
            showcaseRepository.save(showcaseToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        final Showcase showcase = showcaseRepository.findById(id).get();
        if (showcase != null) {
            showcaseRepository.delete(showcase);
            return true;
        }
        return false;
    }
}
