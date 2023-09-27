package study.nextstep.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.nextstep.domain.entity.Play;
import study.nextstep.domain.entity.QPlay;

@RequiredArgsConstructor
public class PlayCustomRepositoryImpl implements PlayCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Play findByTestText(String testText) {
        Play result = queryFactory.selectFrom(QPlay.play)
            .where(QPlay.play.testText.eq(testText))
            .fetchOne();

        return result;
    }
}
