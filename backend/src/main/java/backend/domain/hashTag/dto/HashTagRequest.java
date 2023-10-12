package backend.domain.hashTag.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HashTagRequest {

    @Size(min = 2, message = "2자 이상 입력해주세요.")
    private String name;

}
