package WarehouseAPI.WarehouseAPI.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Table(name = "showcases")
@NoArgsConstructor
@Entity
public class Showcase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    private int size;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Singular
    private List<ShowcasesItem> items;
}
