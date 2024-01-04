package idusw.leafton.model.DTO;


import idusw.leafton.model.entity.Cart;
import idusw.leafton.model.entity.CartItem;
import idusw.leafton.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartItemDTO {
    private Long cartItemId;

    private CartDTO cart;

    private ProductDTO product;

    private int count;

    public static CartItemDTO toCartItemDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartItemId(cartItem.getCartItemId());
        cartItemDTO.setCart(CartDTO.toCartDTO(cartItem.getCart()));
        cartItemDTO.setProduct(ProductDTO.toProductDTO(cartItem.getProduct()));
        cartItemDTO.setCount(cartItem.getCount());

        return cartItemDTO;
    }

}
