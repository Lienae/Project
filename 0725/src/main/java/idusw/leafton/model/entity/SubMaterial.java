package idusw.leafton.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name= "Sub_material")
public class SubMaterial {

    @Id // pk를 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "subMaterialId")
    private Long subMaterialId;

    @Column
    private String name;
}