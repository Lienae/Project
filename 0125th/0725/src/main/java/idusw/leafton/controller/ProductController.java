package idusw.leafton.controller;

import ch.qos.logback.core.model.Model;
import idusw.leafton.model.DTO.*;
import idusw.leafton.model.entity.MainCategory;
import idusw.leafton.model.entity.MainMaterial;
import idusw.leafton.model.entity.Product;
import idusw.leafton.model.entity.SubCategory;
import idusw.leafton.model.service.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/product")
@Controller
@RequiredArgsConstructor // 이 어노테이션은 final이나 @NonNull이 붙은 필드에 대한 생성자를 자동으로 생성해주는 Lombok의 어노테이션
public class ProductController {
    //product page mapping
    private final ProductService productService; //priavte final을 작성해줘야 @RequiredArgsConstructor 어노테이션 의존성 주입이됨
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final MainCategoryService mainCategoryService;
    private final SubCategoryService subCategoryService;
    private final MainMaterialService mainMaterialService;

    @GetMapping (value="/product/{productId}") //상품 상세 페이지
    public String goProduct(@PathVariable Long productId, HttpServletRequest request) {
        ProductDTO productid = productService.getProductById(productId);
        ProductDTO productDTO = productService.viewDetailProduct(productId); //웹에 전달하기위해 객체생성 serviceimpl은 정보를 변한하기위해 사용됨
        List<ProductDTO> products = productService.viewAllProducts(); //추천상품
        List<ReviewDTO> review = reviewService.viewAllReviews(productid); //상품 리뷰 조회
        double ratingAvg = reviewService.getAvgRating(productId); //별점 평균 조회
        request.setAttribute("products", products);
        request.setAttribute("reviews", review);
        request.setAttribute("productDetail", productDTO);
        request.setAttribute("ratingAvg", ratingAvg);
        return "product/product";
    }

    //shop page mapping
    @GetMapping(value="/shop") // 상품 리스트 처음 시작
    public String goShop(HttpServletRequest request){
            List<ProductDTO> products = productService.viewAllProducts();
            List<MainCategoryDTO> mainCategories = mainCategoryService.viewAllMainCategory(); //메인카테고리 조회
            List<MainMaterialDTO> mainMaterials = mainMaterialService.viewAllMainMaterial(); //메인 재료 조회
            request.setAttribute("products", products);
            request.setAttribute("mainCategories", mainCategories);
            request.setAttribute("mainMaterials", mainMaterials);
            return "product/shop";
    }
    @GetMapping(value="/shop/mainCategory") //메인카테고리 조회
    public String goMainCategory(@RequestParam("mainCategoryId") Long mainCategoryId, HttpServletRequest request) {
        MainCategoryDTO mainCategoryDTO = mainCategoryService.getMainCategoryById(mainCategoryId); //Long -> MainCategoryDTO
        List<ProductDTO> products = productService.viewProductsByMainCategory(mainCategoryDTO); //메인카테고리별 상품조회
        List<SubCategoryDTO> subCategories = subCategoryService.getSubCategoryByMainCategoryId(mainCategoryDTO); //서브카테고리 조회
        List<MainMaterialDTO> mainMaterials = mainMaterialService.viewAllMainMaterial(); //메인 재료 조회
        List<MainCategoryDTO> mainCategories = mainCategoryService.viewAllMainCategory(); //메인카테고리 선택후에도 기존에 메인카테고리 선택하기 위함
        MainCategoryDTO mainCategoryDetail = mainCategoryService.getMainCategoryDetail(mainCategoryId); //메인카테고리 선택하면 메인카테고리메뉴에 해당이름 적용 또는 서브제목
        request.setAttribute("subCategories", subCategories);
        request.setAttribute("mainCategoryDetail", mainCategoryDetail);
        request.setAttribute("products", products);
        request.setAttribute("mainCategories", mainCategories);
        request.setAttribute("mainMaterials", mainMaterials);
        return "product/shop";
    }
    @GetMapping(value="/shop/subCategory") //서브카테고리 조회
    public String goSubCategory(@RequestParam("mainCategoryId") Long mainCategoryId, @RequestParam("subCategoryId") Long subCategoryId, HttpServletRequest request) {
        SubCategoryDTO subCategoryDTO = subCategoryService.getSubCategoryById(subCategoryId); //Long -> SubCategoryDTO
        MainCategoryDTO mainCategoryDTO = mainCategoryService.getMainCategoryById(mainCategoryId); //Long -> MainCategoryDTO
        List<ProductDTO> products = productService.viewProductsBySubCategory(mainCategoryDTO, subCategoryDTO); //서브카테고리별 상품조회
        List<SubCategoryDTO> subCategories = subCategoryService.getSubCategoryByMainCategoryId(mainCategoryDTO); //서브카테고리 조회
        List<MainCategoryDTO> mainCategories = mainCategoryService.viewAllMainCategory(); //서브카테고리 선택후에도 기존에 메인카테고리 선택하기 위함
        List<MainMaterialDTO> mainMaterials = mainMaterialService.viewAllMainMaterial(); //메인 재료 조회
        SubCategoryDTO subCategoryDetail = subCategoryService.getSubCategoryDetail(subCategoryId); //서브카테고리 선택하면 서브카테고리메뉴에 선택한 서브카테고리이름 적용
        MainCategoryDTO mainCategoryDetail = mainCategoryService.getMainCategoryDetail(mainCategoryId); //서브카테고리 선택후에도 기존에 선택한 메인카테고리 메뉴를 메인카테고리메뉴에 선택한해당메인카테고리이름 적용
        request.setAttribute("products", products);
        request.setAttribute("subCategories", subCategories);
        request.setAttribute("mainCategoryDetail", mainCategoryDetail);
        request.setAttribute("subCategoryDetail",subCategoryDetail);
        request.setAttribute("mainCategories", mainCategories);
        request.setAttribute("mainMaterials", mainMaterials);
        return "product/shop";
    }

    @GetMapping(value="/shop/material") // 메인 재료만 조회
    public String goMainMaterial(@RequestParam("mainMaterialId") Long mainMaterialId, HttpServletRequest request) {
        MainMaterialDTO mainMaterialDTO = mainMaterialService.getMainMaterialById(mainMaterialId); //Long -> MainMaterialDTO
        List<ProductDTO> products = productService.viewProductsByMainMaterial(mainMaterialDTO); //메인재료 상품 검색
        List<MainMaterialDTO> mainMaterials = mainMaterialService.viewAllMainMaterial(); //메인재료 선택후에도 메인 재료 선택하기 위함
        List<MainCategoryDTO> mainCategories = mainCategoryService.viewAllMainCategory(); //메인카테고리 선택후에도 메인 카테고리 선택하기 위함
        MainMaterialDTO mainMaterialDetail = mainMaterialService.getMainMaterialDetail(mainMaterialId); //메인재료 선택시 메인재료메뉴에 선택한 이름 유지
        request.setAttribute("products",products);
        request.setAttribute("mainMaterialDetail",mainMaterialDetail);
        request.setAttribute("mainMaterials", mainMaterials);
        request.setAttribute("mainCategories",mainCategories);
        return "product/shop";
    }
    @GetMapping(value="/shop/mainCategory-material") // 메인카테고리선택된 상태에서 메인 재료 조회
    public String goMainCategoryAndMaterial(@RequestParam("mainCategoryId") Long mainCategoryId,
                                            @RequestParam("mainMaterialId") Long mainMaterialId, HttpServletRequest request) {
        MainCategoryDTO mainCategoryDTO = mainCategoryService.getMainCategoryById(mainCategoryId); //Long -> MainCategoryDTO
        MainMaterialDTO mainMaterialDTO = mainMaterialService.getMainMaterialById(mainMaterialId); //Long -> MainMaterialDTO
        List<ProductDTO> products = productService.viewProductsByMcAndMm(mainCategoryDTO, mainMaterialDTO); //메인카테고리, 메인재료 상품조회
        List<MainCategoryDTO> mainCategories = mainCategoryService.viewAllMainCategory(); //메인카테고리,메인재료 선택후에도 메인카테고리 선택하기 위함
        List<SubCategoryDTO> subCategories = subCategoryService.getSubCategoryByMainCategoryId(mainCategoryDTO); //서브카테고리 조회
        List<MainMaterialDTO> mainMaterials = mainMaterialService.viewAllMainMaterial(); //메인재료 선택후에도 메인 재료 조회하기 위함
        MainMaterialDTO mainMaterialDetail = mainMaterialService.getMainMaterialDetail(mainMaterialId); //메인재료,메인카테고리 선택시 메인재료메뉴에 선택한 이름 적용
        MainCategoryDTO mainCategoryDetail = mainCategoryService.getMainCategoryDetail(mainCategoryId); //메인재료,메인카테고리 선택시 메인카테고리메뉴에 해당이름 적용
        request.setAttribute("products", products);
        request.setAttribute("mainCategories", mainCategories);
        request.setAttribute("subCategories", subCategories);
        request.setAttribute("mainMaterials", mainMaterials);
        request.setAttribute("mainCategoryDetail", mainCategoryDetail);
        request.setAttribute("mainMaterialDetail", mainMaterialDetail);
        return "product/shop";
    }

    @GetMapping(value="/shop/mainCategory-subCategory-material")
    public String goMainCategoryAndSubCategoryAndMaterial(@RequestParam("mainCategoryId") Long mainCategoryId,
                                                          @RequestParam("subCategoryId") Long subCategoryId,
                                                          @RequestParam("mainMaterialId") Long mainMaterialId, HttpServletRequest request){
        MainCategoryDTO mainCategoryDTO = mainCategoryService.getMainCategoryById(mainCategoryId); //Long -> MainCategoryDTO
        MainMaterialDTO mainMaterialDTO = mainMaterialService.getMainMaterialById(mainMaterialId); //Long -> MainMaterialDTO
        SubCategoryDTO subCategoryDTO = subCategoryService.getSubCategoryById(subCategoryId); //Long -> SubCategoryDTO
        List<ProductDTO> products = productService.viewProductsByMcAndScAndMm(mainCategoryDTO,subCategoryDTO,mainMaterialDTO); //메인카테고리-서브카테고리,메인재료 상품조회
        List<MainCategoryDTO> mainCategories = mainCategoryService.viewAllMainCategory(); //서브카테고리-재료 선택후에도 기존에 메인카테고리 선택하기 위함
        List<SubCategoryDTO> subCategories = subCategoryService.getSubCategoryByMainCategoryId(mainCategoryDTO); //서브카테고리-재료 선택후에도 서브카테고리 선택하기위함
        List<MainMaterialDTO> mainMaterials = mainMaterialService.viewAllMainMaterial(); //서브카테고리-재료 선택후에도 메인 재료 선택하기위함
        SubCategoryDTO subCategoryDetail = subCategoryService.getSubCategoryDetail(subCategoryId); //서브카테고리 선택하면 서브카테고리메뉴에 선택한 서브카테고리이름 적용
        MainMaterialDTO mainMaterialDetail = mainMaterialService.getMainMaterialDetail(mainMaterialId); //메인재료,메인카테고리 선택시 메인재료메뉴에 선택한 이름 적용
        MainCategoryDTO mainCategoryDetail = mainCategoryService.getMainCategoryDetail(mainCategoryId); //메인재료,메인카테고리 선택시 메인카테고리메뉴에 해당이름 적용
        request.setAttribute("products", products);
        request.setAttribute("mainCategories", mainCategories);
        request.setAttribute("subCategories", subCategories);
        request.setAttribute("mainMaterials", mainMaterials);
        request.setAttribute("subCategoryDetail", subCategoryDetail);
        request.setAttribute("mainCategoryDetail", mainCategoryDetail);
        request.setAttribute("mainMaterialDetail", mainMaterialDetail);
        return "product/shop";
    }

    @PostMapping(value="/review") //리뷰작성
    public String goReview(HttpServletRequest request){
        ReviewDTO reviewDTO = new ReviewDTO();
        MemberDTO memberDTO = memberService.getMemberById(Long.valueOf(request.getParameter("memberId")));
        ProductDTO productDTO = productService.getProductById(Long.valueOf(request.getParameter("productId")));

        reviewDTO.setMemberDTO(memberDTO);
        reviewDTO.setProductDTO(productDTO);
        reviewDTO.setRating(Integer.valueOf(request.getParameter("rating")));
        reviewDTO.setContent(request.getParameter("reviewContent"));
        reviewDTO.setRegistDate(LocalDate.now());

        reviewService.insertReview(reviewDTO);
        return "redirect:/product/product/" + Long.valueOf(request.getParameter("productId"));
    }
}