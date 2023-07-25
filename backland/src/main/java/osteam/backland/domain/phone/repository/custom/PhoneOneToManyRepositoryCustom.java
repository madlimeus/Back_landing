package osteam.backland.domain.phone.repository.custom;

import osteam.backland.domain.phone.entity.PhoneOneToMany;

import java.util.List;

public interface PhoneOneToManyRepositoryCustom {
    List<PhoneOneToMany> findByPhone(String phone);
}
