package study.nextstep.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.repository.jpa.PlayRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PlayTx {

    private final PlayRepository playRepository;

    public void save(Play play){
        playRepository.save(play);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAndFlushRequiresNew(Play play){
        playRepository.saveAndFlush(play);
    }

    public void saveAndFlush(Play play){
        playRepository.saveAndFlush(play);
    }

    public Play find(String id){

        return playRepository.findById(id).orElse(null);
    }

    public Play findByTestText(String testText){

        return playRepository.findByTestText(testText);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Play findRequiresNew(String id) throws NotFoundException {

        return playRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
