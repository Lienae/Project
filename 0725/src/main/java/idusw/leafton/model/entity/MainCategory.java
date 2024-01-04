package idusw.leafton.model.entity;

import idusw.leafton.model.DTO.SubCategoryDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Main_category")
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "mainCategoryId")
    private Long mainCategoryId;

    @Column
    private String name;

    public static SubCategory toSubCategoryEntity(SubCategoryDTO subCategoryDTO){

        SubCategory subCategory = new SubCategory();

        subCategory.setSubCategoryId(subCategoryDTO.getSubCategoryId());
        subCategory.setMainCategory(subCategoryDTO.getMainCategory());
        subCategory.setName(subCategoryDTO.getName());

        return subCategory;
    }
}
