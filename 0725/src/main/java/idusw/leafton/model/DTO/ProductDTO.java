package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {

    private Long productId;

    private MainCategory mainCategory;

    private MainMaterial mainMaterial;

    private StyleDTO style;

    private String content;

    private String name;

    private String color;

    private String size;

    private Integer price;

    private Integer salePercentage;

    private Integer weight;

    private Integer asPeriod;

    private String registDate;

    private Integer amount;

    private Integer isAssemble;

    private String mainImage;

    private String subImage;

    private String thumbImage;


    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(product.getProductId());
        productDTO.setMainCategory(product.getMainCategory());
        productDTO.setMainMaterial(product.getMainMaterial());
        productDTO.setStyle(StyleDTO.toStyleDTO(product.getStyle()));
        productDTO.setContent(product.getContent());
        productDTO.setName(product.getName());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setPrice(product.getPrice());
        productDTO.setSalePercentage(product.getSalePercentage());
        productDTO.setWeight(product.getWeight());
        productDTO.setAsPeriod(product.getAsPeriod());
        productDTO.setRegistDate(product.getRegistDate());
        productDTO.setAmount(product.getAmount());
        productDTO.setIsAssemble(product.getIsAssemble());
        productDTO.setMainImage(product.getMainImage());
        productDTO.setSubImage(product.getSubImage());
        productDTO.setThumbImage(product.getThumbImage());

        return productDTO;
    }

}
