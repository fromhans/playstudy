package study.nextstep.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Play {

    @Builder
    public Play(String testText) {
        this.id =  UUID.randomUUID().toString();
        this.testText = testText;
    }

    @Id
    @Column(name = "play_id")
    private String id;

    private String testText;


}
