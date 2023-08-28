package backend.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE member SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
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
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(final String username, final String password, final String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;

        if (email != null) {
            this.email = email;
        } else {
            this.email = null;
        }

        this.title = null;
        this.image = null;
        this.createdAt = LocalDateTime.now();
        this.status = MemberStatus.ACTIVE;
    }

    public Member updateMemberPasswordAndNickname(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
        this.modifiedAt = LocalDateTime.now();

        return this;
    }

    public Member updateMemberEmail(String email) {
        this.email = email;
        this.modifiedAt = LocalDateTime.now();

        return this;
    }

    public Member updateMemberTitle(String title) {
        this.title = title;
        this.modifiedAt = LocalDateTime.now();

        return this;
    }

    public Member updateMemberProfileImage(String path) {
        this.image = path;
        this.modifiedAt = LocalDateTime.now();

        return this;
    }

    public Member deleteMemberProfileImage() {
        this.image = null;
        this.modifiedAt = LocalDateTime.now();

        return this;
    }

    public Member updateStatus(MemberStatus status) {
        this.status = status;

        return this;
    }

    public boolean isAdmin() {
        return username.equals("admin");
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        if (isAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return grantedAuthorities;
    }

}
