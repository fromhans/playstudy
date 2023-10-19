package study.nextstep.event.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import study.nextstep.domain.entity.EntityA;

@RequiredArgsConstructor
@Slf4j
public class EntityAListener {

    @PostPersist
    void AfterCreateProcess(EntityA entityA){
        log.info("Entity A Listener, @PostPersist");
    }

    @PreRemove
    void BeforeDeleteProcess(EntityA entityA){
        log.info("Entity A Listener, @PreRemove");
    }
}
