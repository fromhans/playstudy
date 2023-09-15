package study.nextstep.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayCustomRepositoryImpl implements PlayCustomRepository {
    private final JPAQueryFactory queryFactory;

}
