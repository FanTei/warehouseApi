package WarehouseAPI.WarehouseAPI.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "items")
@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int occupiedSize;
    private double price;
}
