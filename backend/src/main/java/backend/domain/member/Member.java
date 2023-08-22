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
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nickname;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(final String username, final String password, final String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
        this.status = MemberStatus.ACTIVE;
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        if (isAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return grantedAuthorities;
    }

    public boolean isAdmin() {
        return username.equals("admin");
    }

}
