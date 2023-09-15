package study.nextstep.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.repository.PlayCustomRepository;

public interface PlayRepository extends JpaRepository<Play,String>, PlayCustomRepository {

}
