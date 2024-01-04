package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long memberId;

    private String address;

    private int zipcode;

    private StyleDTO style;

    private String email;

    private String password;

    private int age;

    private int gender;

    private String name;

    private String phone;

    public static MemberDTO toMemberDTO(Member member){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setAddress(member.getAddress());
        memberDTO.setStyle(StyleDTO.toStyleDTO(member.getStyle()));
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setAge(member.getAge());
        memberDTO.setGender(member.getGender());
        memberDTO.setName(member.getName());
        memberDTO.setPhone(member.getPhone());

        return memberDTO;
    }

}
