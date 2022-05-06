package WarehouseAPI.WarehouseAPI.service;

import WarehouseAPI.WarehouseAPI.entity.Showcase;

import java.util.List;

public interface ShowcaseService {
    void create(Showcase showcase);

    List<Showcase> readAll();

    Showcase read(Long id);

    boolean update(Showcase showcase, Long id);

    boolean delete(Long id);
}
