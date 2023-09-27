package study.nextstep.domain.repository;

import study.nextstep.domain.entity.Play;

public interface PlayCustomRepository {

    Play findByTestText(String testText);
}
