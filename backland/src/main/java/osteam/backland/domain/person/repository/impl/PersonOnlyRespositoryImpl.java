package osteam.backland.domain.person.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.repository.custom.PersonOnlyRespositoryCustom;

import java.util.List;

import static osteam.backland.domain.person.entity.QPersonOnly.personOnly;

@RequiredArgsConstructor
@Transactional
public class PersonOnlyRespositoryImpl implements PersonOnlyRespositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<PersonOnly> findByName(String name) {
        List<PersonOnly> result = queryFactory
                .selectFrom(personOnly)
                .where(personOnly.name.eq(name))
                .fetch();
        return result;
    }

    @Override
    public List<PersonOnly> findByPhone(String phone) {
        List<PersonOnly> result = queryFactory
                .selectFrom(personOnly)
                .where(personOnly.phone.eq(phone))
                .fetch();
        return result;
    }

    @Override
    public void updateByPhone(String phone, String name) {
        queryFactory
                .update(personOnly)
                .set(personOnly.name, name)
                .where(personOnly.phone.eq(phone))
                .execute();
    }
}
