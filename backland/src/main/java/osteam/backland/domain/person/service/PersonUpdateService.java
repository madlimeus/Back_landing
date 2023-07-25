package osteam.backland.domain.person.service;

import org.springframework.stereotype.Service;
import osteam.backland.domain.person.controller.request.PersonRequest;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRespository;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.repository.PhoneOneToManyRepository;
import osteam.backland.domain.phone.repository.PhoneOneToOneRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PersonUpdateService {

    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;
    private final PersonOnlyRespository personOnlyRespository;
    private final PhoneOneToOneRepository phoneOneToOneRepository;
    private final PhoneOneToManyRepository phoneOneToManyRepository;
    public PersonUpdateService(
            PersonOneToOneRepository personOneToOneRepository,
            PersonOneToManyRepository personOneToManyRepository,
            PersonOnlyRespository personOnlyRespository,
            PhoneOneToOneRepository phoneOneToOneRepository,
            PhoneOneToManyRepository phoneOneToManyRepository
    ){
        this.personOneToOneRepository = personOneToOneRepository;
        this.personOneToManyRepository = personOneToManyRepository;
        this.personOnlyRespository = personOnlyRespository;
        this.phoneOneToOneRepository = phoneOneToOneRepository;
        this.phoneOneToManyRepository = phoneOneToManyRepository;
    }
    public void modify(PersonRequest person){
        String phone = person.getPhone();
        String name = person.getName();

        List<PhoneOneToMany> phoneMany = phoneOneToManyRepository.findByPhone(phone);
        List<PhoneOneToOne> phoneOne = phoneOneToOneRepository.findByPhone(phone);

        UUID idMany = null, idOne = null;
        if(!phoneMany.isEmpty() && !phoneOne.isEmpty()){
            idMany = phoneMany.get(0).getPersonOneToMany().getId();
            idOne  = phoneOne.get(0).getPersonOneToOne().getId();
        }
        personOneToManyRepository.updateById(idMany, name);
        personOneToOneRepository.updateById(idOne, name);
        personOnlyRespository.updateByPhone(phone, name);

    }
}
