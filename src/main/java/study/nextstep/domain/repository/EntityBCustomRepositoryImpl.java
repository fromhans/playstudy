package study.nextstep.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntityBCustomRepositoryImpl implements
    EntityBCustomRepository {
    private final JPAQueryFactory queryFactory;


}
