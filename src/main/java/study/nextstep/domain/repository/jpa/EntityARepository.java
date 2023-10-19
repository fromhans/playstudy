package study.nextstep.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import study.nextstep.domain.entity.EntityA;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.repository.EntityACustomRepository;
import study.nextstep.domain.repository.PlayCustomRepository;

public interface EntityARepository extends JpaRepository<EntityA,String>, EntityACustomRepository {

}
