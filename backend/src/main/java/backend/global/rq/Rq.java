package backend.global.rq;

import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
@RequestScope
public class Rq {
    private final MemberService memberService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private Locale locale;
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;
    private final User user;
    private Member member = null; // 레이지 로딩, 처음부터 넣지 않고, 요청이 들어올 때 넣는다.

    public Rq(MemberService memberService, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        this.memberService = memberService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.req = req;
        this.resp = resp;
        this.session = session;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            this.user = (User) authentication.getPrincipal();
        } else {
            this.user = null;
        }
    }

    public boolean isLogin() {
        return user != null;
    }

    public boolean isLogout() {
        return !isLogin();
    }

    public Member getMember() {
        if (isLogout()) return null;

        if (member == null) {
            member = memberService.findByUsername(user.getUsername());
        }

        return member;
    }
}