package osteam.backland.domain.person.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonRequest {
    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 영어/한국어만 가능합니다")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phone;
}
