package study.nextstep.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.repository.jpa.PlayRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayRx {

    private final PlayRepository playRepository;

    public Play find(String id){

        return playRepository.findById(id).orElse(null);
    }

    public Play findByTestText(String testText){

        return playRepository.findByTestText(testText);
    }
}
