package idusw.leafton.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="MainMaterial")
public class MainMaterial {

    @Id // pk를 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "mainMaterialId")
    private Long mainMaterialId;

    @OneToOne
    @JoinColumn(name = "subMaterialId")
    private SubMaterial subMaterial;

    @Column
    private String name;
}