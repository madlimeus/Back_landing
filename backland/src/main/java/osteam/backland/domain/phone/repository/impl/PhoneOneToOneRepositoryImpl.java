package osteam.backland.domain.phone.repository.impl;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.repository.custom.PhoneOneToOneRepositoryCustom;

import static osteam.backland.domain.person.entity.QPersonOneToOne.personOneToOne;
import static osteam.backland.domain.phone.entity.QPhoneOneToOne.phoneOneToOne;

@RequiredArgsConstructor
public class PhoneOneToOneRepositoryImpl implements PhoneOneToOneRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<PhoneOneToOne> findByPhone(String phone) {
        List<PhoneOneToOne> result = queryFactory
                .selectFrom(phoneOneToOne)
                .leftJoin(phoneOneToOne).on(personOneToOne.id.eq(phoneOneToOne.personOneToOne.id))
                .where(phoneOneToOne.phone.eq(phone))
                .fetch();
        return result;
    }
}
