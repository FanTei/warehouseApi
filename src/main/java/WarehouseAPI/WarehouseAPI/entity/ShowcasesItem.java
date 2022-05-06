package WarehouseAPI.WarehouseAPI.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class ShowcasesItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @NonNull
    private Item item;
    @NonNull
    private int quantity;
}