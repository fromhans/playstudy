package study.nextstep.domain.entity;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.nextstep.event.listener.EntityAListener;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(EntityAListener.class)
public class EntityA {

    @Builder
    public EntityA(String testText) {
        this.id =  UUID.randomUUID().toString();
        this.testText = testText;
    }

    @Id
    @Column(name = "play_id")
    private String id;

    private String testText;

    private String newColumn;

    @OneToOne(fetch = LAZY, mappedBy = "entityAId", cascade= {CascadeType.REMOVE}, orphanRemoval = true)
    private EntityB entityBId;
}
