package osteam.backland.domain.phone.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.repository.custom.PhoneOneToManyRepositoryCustom;

import java.util.List;

import static osteam.backland.domain.person.entity.QPersonOneToMany.personOneToMany;
import static osteam.backland.domain.phone.entity.QPhoneOneToMany.phoneOneToMany;

@RequiredArgsConstructor
public class PhoneOneToManyRepositoryImpl implements PhoneOneToManyRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PhoneOneToMany> findByPhone(String phone) {
        List<PhoneOneToMany> result = queryFactory
                .selectFrom(phoneOneToMany)
                .leftJoin(phoneOneToMany).on(personOneToMany.id.eq(phoneOneToMany.personOneToMany.id))
                .where(phoneOneToMany.phone.eq(phone))
                .fetch();
        return result;
    }
}
