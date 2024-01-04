package idusw.leafton.model.service;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.ProductDTO;
import idusw.leafton.model.entity.Cart;
import idusw.leafton.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    @Override
    public void addCart(MemberDTO member, ProductDTO product, int count){
        Optional<Cart> findCart = cartRepository.findByMember_MemberId(member.getMemberId());

        if(findCart == null){
            Cart cart = Cart.createCart(member);
            cartRepository.save(cart);
        }


    }
}
