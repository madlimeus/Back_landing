package osteam.backland.domain.person.service;

import org.springframework.stereotype.Service;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.repository.PersonOnlyRespository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonReadService {
    private final PersonOnlyRespository personOnlyRespository;

    public PersonReadService(
            PersonOnlyRespository personOnlyRespository
    ){
        this.personOnlyRespository = personOnlyRespository;
    }

    public List<PersonResponse> readAll(){
        List<PersonResponse> result = new ArrayList<>();
        List<PersonOnly> personOnlyList = personOnlyRespository.findAll();
        Map<String, List<String>> PhoneMap = new HashMap<>();

        for(PersonOnly person : personOnlyList){
            if(!PhoneMap.containsKey(person.getName())){
                List<String> tmpPhoneList = new ArrayList<>();
                tmpPhoneList.add(person.getPhone());
                PhoneMap.put(person.getName(), tmpPhoneList);
            } else {
                List<String> tmpPhoneList = PhoneMap.get(person.getName());
                tmpPhoneList.add(person.getPhone());
                PhoneMap.put(person.getName(), tmpPhoneList);
            }
        }
        PhoneMap.forEach((key, value) ->{
            PersonResponse tmp = new PersonResponse();
            tmp.setName(key);
            tmp.setPhoneList(value);
            result.add(tmp);
        });

        return result;
    }

    public List<PersonResponse> readName(String name){
        List<PersonResponse> result = new ArrayList<>();
        List<PersonOnly> personOnlyList = personOnlyRespository.findByName(name);

        List<String> tmpPhone = new ArrayList<>();
        for(PersonOnly person : personOnlyList){
            tmpPhone.add(person.getPhone());
        }
        PersonResponse tmp = new PersonResponse();
        tmp.setName(name);
        tmp.setPhoneList(tmpPhone);

        result.add(tmp);
        return result;
    }


    public List<PersonResponse> readPhone(String phone){
        List<PersonResponse> result = new ArrayList<>();
        List<PersonOnly> personOnlyList = personOnlyRespository.findByPhone(phone);

        List<String> tmpPhone = new ArrayList<>();
        tmpPhone.add(phone);

        PersonResponse tmp = new PersonResponse();
        tmp.setName(personOnlyList.get(0).getName());
        tmp.setPhoneList(tmpPhone);

        result.add(tmp);
        return result;
    }
}
