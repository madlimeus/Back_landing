package osteam.backland.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.repository.custom.PersonOneToOneRepositoryCustom;

import java.util.UUID;

public interface PersonOneToOneRepository extends JpaRepository<PersonOneToOne, UUID>, PersonOneToOneRepositoryCustom {
}
