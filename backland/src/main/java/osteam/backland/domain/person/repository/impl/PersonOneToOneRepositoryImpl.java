package osteam.backland.domain.person.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.repository.custom.PersonOneToOneRepositoryCustom;

import java.util.UUID;

import static osteam.backland.domain.person.entity.QPersonOneToMany.personOneToMany;
import static osteam.backland.domain.person.entity.QPersonOneToOne.personOneToOne;

@RequiredArgsConstructor
@Transactional
public class PersonOneToOneRepositoryImpl implements PersonOneToOneRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public void updateById(UUID id, String name) {
        queryFactory
                .update(personOneToOne)
                .set(personOneToOne.name, name)
                .where(personOneToOne.id.eq(id))
                .execute();
    }

}
