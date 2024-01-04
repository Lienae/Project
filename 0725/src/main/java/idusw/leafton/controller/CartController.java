package idusw.leafton.controller;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.ProductDTO;
import idusw.leafton.model.service.CartService;
import idusw.leafton.model.service.MemberService;
import idusw.leafton.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/pay")
@RequiredArgsConstructor
@Controller
public class CartController {

    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping(value = "/cart")
    public String goCart(){

        return "pay/cart";
    }

    @GetMapping(value = "/buy")
    public String goBuy(){
        return "pay/buy";
    }

    @GetMapping(value = "/complete")
    public String goComplete(){
        return "pay/complete";
    }

    @PostMapping(value = "/cart/{memberId}/{productId}")
    public void testCart(@PathVariable("memberId") Long memberId, @PathVariable("productId") Long productId, int count) {

       MemberDTO member = memberService.getMemberById(memberId);
       ProductDTO product = productService.getProductDTOId(productId);

       cartService.addCart(member, product, count);

       System.out.println("complete");
    }

}


