package WarehouseAPI.WarehouseAPI.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table(name = "items")
@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    private int occupiedSize;
    private double price;
}
