package backend.global.entity;

import backend.global.StatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static backend.global.StatusType.*;

@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status = USABLE;

    protected BaseEntity(final StatusType status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return this.status.equals(DELETED);
    }

    public void changeStatusToDeleted() {
        this.status = DELETED;
    }

}
