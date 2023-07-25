package osteam.backland.domain.person.repository.custom;

import java.util.UUID;

public interface PersonOneToOneRepositoryCustom {
    void updateById(UUID id, String name);
}
