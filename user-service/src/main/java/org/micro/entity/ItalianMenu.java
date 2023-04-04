package org.micro.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "italian_menu")
@Builder
@Entity
public class ItalianMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "prize")
    private String prize;

    @Column(name = "menuId")
    private Long menuId;

    @Column(name = "item_name")
    private String itemName;
}
