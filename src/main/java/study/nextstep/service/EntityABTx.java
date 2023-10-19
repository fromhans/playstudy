package study.nextstep.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.nextstep.domain.entity.EntityA;
import study.nextstep.domain.repository.jpa.EntityARepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class EntityABTx {

    private final EntityARepository entityARepository;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void extracted() {
        EntityA entityA2 = new EntityA("entityA2");
        //when
        entityARepository.save(entityA2);
    }
}
