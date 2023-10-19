package study.nextstep.event.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PreRemove;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.nextstep.domain.entity.EntityA;
import study.nextstep.domain.entity.EntityB;
import study.nextstep.domain.repository.jpa.EntityARepository;
import study.nextstep.domain.repository.jpa.EntityBRepository;
import study.nextstep.domain.utils.AsyncExecutor;
import study.nextstep.service.EntityABTx;

@RequiredArgsConstructor
@Slf4j
public class EntityBListener {

    @Autowired
    private final EntityARepository entityARepository;
    @Autowired
    private final EntityBRepository entityBRepository;
    @Autowired
    private final EntityABTx entityABTx;
    @Autowired
    private final AsyncExecutor asyncExecutor;

    @PostPersist
    void AfterCreateProcess(EntityB entityB){
        log.info("Entity B Listener, @PostPersist");
        //given
        entityABTx.extracted();

    }

    @PreRemove
    void BeforeDeleteProcess(EntityB entityB) {
        log.info("Entity B Listener, @PreRemove");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerExtracted() {
        EntityA entityA2 = new EntityA("entityA2");
        //when
        entityARepository.save(entityA2);
    }
}