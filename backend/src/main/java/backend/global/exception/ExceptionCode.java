package backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    UNAUTHORIZED(0000, "권한이 없습니다."),

    MEMBER_NOT_FOUND(1000, "존재하지 않는 회원입니다."),
    MEMBER_USERNAME_ALREADY_EXISTS(1001, "이미 존재하는 회원입니다."),
    MEMBER_PASSWORD_DO_NOT_MATCH(1002, "비밀번호가 일치하지 않습니다."),

    POST_NOT_FOUND(2000, "존재하지 않는 글입니다."),

    COMMENT_NOT_FOUND(3000, "존재하지 않는 댓글입니다."),

    SUB_COMMENT_NOT_FOUND(4000, "존재하지 않는 대댓글입니다."),

    CATEGORY_NOT_FOUND(5000, "존재하지 않는 카테고리입니다."),
    
    TAG_NOT_FOUND(6000, "존재하지 않는 태그입니다."),

    LIKED_NOT_FOUND(7000, "존재하지 않는 좋아요 정보입니다."),
    LIKED_DUPLICATE(7001, "좋아요는 게시글 당 한 번만 가능합니다.");

    private final int code;
    private final String message;

}