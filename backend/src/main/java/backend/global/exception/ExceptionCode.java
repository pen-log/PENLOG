package backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    MEMBER_NOT_FOUND(1000, "존재하지 않는 회원입니다."),
    MEMBER_USERNAME_ALREADY_EXISTS(1001, "이미 존재하는 회원입니다.");

    private final int code;
    private final String message;
}