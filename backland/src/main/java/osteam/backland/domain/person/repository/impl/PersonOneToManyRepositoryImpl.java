package osteam.backland.domain.person.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.repository.custom.PersonOneToManyRepositoryCustom;

import java.util.UUID;

import static osteam.backland.domain.person.entity.QPersonOneToMany.personOneToMany;
import static osteam.backland.domain.person.entity.QPersonOneToOne.personOneToOne;

@RequiredArgsConstructor
@Transactional
public class PersonOneToManyRepositoryImpl implements PersonOneToManyRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public void updateById(UUID id, String name) {
        queryFactory
                .update(personOneToMany)
                .set(personOneToMany.name, name)
                .where(personOneToMany.id.eq(id))
                .execute();
    }
}
