package osteam.backland.domain.person.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.List;
import java.util.UUID;

public interface PersonOneToManyRepositoryCustom {
    void updateById(UUID id, String name);
}
