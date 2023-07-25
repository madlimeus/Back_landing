package osteam.backland.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.repository.custom.PersonOnlyRespositoryCustom;

import java.util.UUID;

public interface PersonOnlyRespository extends JpaRepository<PersonOnly, UUID>, PersonOnlyRespositoryCustom {
}
