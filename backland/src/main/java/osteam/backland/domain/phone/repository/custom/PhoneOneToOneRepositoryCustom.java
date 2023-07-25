package osteam.backland.domain.phone.repository.custom;

import java.util.List;
import osteam.backland.domain.phone.entity.PhoneOneToOne;

import java.util.Optional;

public interface PhoneOneToOneRepositoryCustom {
    List<PhoneOneToOne> findByPhone(String phone);
}
