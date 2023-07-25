package osteam.backland.domain.person.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.controller.request.PersonRequest;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRespository;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.repository.PhoneOneToManyRepository;
import osteam.backland.domain.phone.repository.PhoneOneToOneRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonCreateService {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;
    private final PersonOnlyRespository personOnlyRespository;
    private final PhoneOneToOneRepository phoneOneToOneRepository;
    private final PhoneOneToManyRepository phoneOneToManyRepository;
    public PersonCreateService(
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


    public boolean join(PersonRequest person){
        boolean result = false;
        if(!duplicatePerson(person)){
            personOneToOneRepository.save(makeOneToOne(person));
            personOneToManyRepository.save(makeOneToMany(person));
            personOnlyRespository.save(makePersonOnly(person));
        } else {
            result = true;
        }
        return result;
    }


    public PersonOnly makePersonOnly(PersonRequest person){
        PersonOnly personOnly = new PersonOnly();
        personOnly.setName(person.getName());
        personOnly.setPhone(person.getPhone());
        return personOnly;
    }
    public PersonOneToOne makeOneToOne(PersonRequest person){
        PersonOneToOne personOneToOne = new PersonOneToOne();
        PhoneOneToOne phoneOneToOne = new PhoneOneToOne();

        phoneOneToOne.setPhone(person.getPhone());
        phoneOneToOne.setPersonOneToOne(personOneToOne);

        personOneToOne.setName(person.getName());
        personOneToOne.setPhoneOneToOne(phoneOneToOne);

        return personOneToOne;
    }

    public PersonOneToMany makeOneToMany(PersonRequest person){
        PersonOneToMany personMany = new PersonOneToMany();
        PhoneOneToMany phone = new PhoneOneToMany();
        List<PhoneOneToMany> phoneList = new ArrayList<>();

        phone.setPhone(person.getPhone());
        phone.setPersonOneToMany(personMany);
        phoneList.add(phone);

        personMany.setName(person.getName());
        personMany.setPhoneOneToMany(phoneList);

        return personMany;
    }

    public boolean duplicatePerson(PersonRequest person){
        boolean result = false;
        String phone = person.getPhone();
        if(!phoneOneToOneRepository.findByPhone(phone).isEmpty() && !phoneOneToManyRepository.findByPhone(phone).isEmpty()){
            log.info("중복된 전화번호가 들어와 사용자 정보를 수정합니다.");
            result = true;
        }
        return result;
    }
}
