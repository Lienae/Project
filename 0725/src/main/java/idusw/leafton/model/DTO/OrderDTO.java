package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Long orderId;

    private MemberDTO member;

    private CartDTO cart;

    private String address;

    private int zipcode;

    private LocalDate orderDate;

    private int orderPrice;

    private String paymentMethod;

    private int deliveryFee;

    private LocalDate deliveryPeriod;

    private String deliveryCompany;

    private String customerRequest;

    public static OrderDTO toOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setMember(MemberDTO.toMemberDTO(order.getMember()));
        orderDTO.setAddress(order.getAddress());
        orderDTO.setZipcode(order.getZipcode());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderPrice(order.getOrderPrice());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setDeliveryFee(order.getDeliveryFee());
        orderDTO.setDeliveryCompany(order.getDeliveryCompany());
        orderDTO.setCustomerRequest(order.getCustomerRequest());

        return orderDTO;
    }
}
