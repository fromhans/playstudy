package study.nextstep.domain.entity;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.nextstep.event.listener.EntityAListener;
import study.nextstep.event.listener.EntityBListener;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(EntityBListener.class)
public class EntityB {

    @Builder
    public EntityB(String testText) {
        this.id =  UUID.randomUUID().toString();
        this.testText = testText;
    }

    @Id
    @Column(name = "play_id")
    private String id;

    private String testText;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "entity_a_id", nullable = false)
    private EntityA entityAId;

}
