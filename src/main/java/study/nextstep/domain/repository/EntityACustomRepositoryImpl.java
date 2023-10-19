package study.nextstep.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntityACustomRepositoryImpl implements
    EntityACustomRepository {
    private final JPAQueryFactory queryFactory;


}
