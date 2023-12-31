package backend.domain.member;

import backend.domain.member.dto.MemberUpdateRequest;
import backend.global.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static backend.global.exception.ExceptionCode.MEMBER_PASSWORD_DO_NOT_MATCH;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE member SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Column(name = "member_title")
    private String title;

    @Column(name = "image_path")
    private String image;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(final String username, final String password, final String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.title = null;
        this.image = null;
        this.status = MemberStatus.ACTIVE;
    }

    public Member updatePasswordAndNickname(MemberUpdateRequest request) {
        if (!request.getPassword1().equals(request.getPassword2())) {
            throw new BadRequestException(MEMBER_PASSWORD_DO_NOT_MATCH);
        }

        this.password = request.getPassword1();
        this.nickname = request.getNickname();

        return this;
    }

    public Member updateEmail(String email) {
        this.email = email;

        return this;
    }

    public Member updateMemberTitle(String title) {
        this.title = title;

        return this;
    }

    public Member updateProfileImage(String path) {
        this.image = path;

        return this;
    }

    public Member deleteProfileImage() {
        this.image = null;

        return this;
    }

    public void changeStatus(MemberStatus status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return username.equals("admin");
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (isAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return grantedAuthorities;
    }

}
