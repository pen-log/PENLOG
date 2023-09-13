package backend.domain.hashTag.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HashTagCreateRequest {

    @Size(min = 2, message = "2자 이상 입력해주세요.")
    private String name;

}
