package study.nextstep.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import study.nextstep.domain.entity.EntityB;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.repository.EntityACustomRepository;
import study.nextstep.domain.repository.EntityBCustomRepository;
import study.nextstep.domain.repository.PlayCustomRepository;

public interface EntityBRepository extends JpaRepository<EntityB,String>, EntityBCustomRepository {

}
