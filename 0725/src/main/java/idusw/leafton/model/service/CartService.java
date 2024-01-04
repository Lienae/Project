package idusw.leafton.model.service;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.ProductDTO;

public interface CartService {
    void addCart(MemberDTO member, ProductDTO product, int count);
}
