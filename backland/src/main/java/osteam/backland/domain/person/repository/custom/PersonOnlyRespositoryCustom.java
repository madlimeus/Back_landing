package osteam.backland.domain.person.repository.custom;

import osteam.backland.domain.person.entity.PersonOnly;

import java.util.List;

public interface PersonOnlyRespositoryCustom {
    List<PersonOnly> findByName(String name);
    List<PersonOnly> findByPhone(String phone);

    void updateByPhone(String phone, String name);

}
