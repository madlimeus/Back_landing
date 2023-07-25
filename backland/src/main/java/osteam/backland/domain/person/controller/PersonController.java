package osteam.backland.domain.person.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.controller.request.PersonRequest;
import osteam.backland.domain.person.service.PersonReadService;
import osteam.backland.domain.person.service.PersonUpdateService;
import osteam.backland.domain.person.service.ValidateHandlingService;

import java.awt.*;
import java.util.List;
import java.util.Map;

@RestController("/person")
@RequestMapping("/person")
public class PersonController {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private PersonCreateService personCreateService;
    private PersonUpdateService personUpdateService;
    private PersonReadService personReadService;
    private ValidateHandlingService validateHandlingService;

    @Autowired PersonController(
            PersonCreateService personCreateService,
            PersonUpdateService personUpdateService,
            PersonReadService personReadService,
            ValidateHandlingService validateHandlingService
    ){
        this.personCreateService = personCreateService;
        this.personUpdateService = personUpdateService;
        this.personReadService = personReadService;
        this.validateHandlingService = validateHandlingService;
    }


    //Create
    @PostMapping("/create")
    public void memberCreate(@RequestBody @Valid PersonRequest personRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> validateResult = validateHandlingService.validateHandling(bindingResult);
            log.error(validateResult.toString());
            return;
        }
        if(personCreateService.join(personRequest)) {
            this.memberUpdate(personRequest);
        }
    }

    public void memberUpdate(PersonRequest person){
        personUpdateService.modify(person);
    }

    //Read
    @GetMapping("/read")
    public List<PersonResponse> personReadAll(){
        return personReadService.readAll();
    }

    @GetMapping("/read/name")
    public List<PersonResponse> personReadName(@RequestParam String name){
        return personReadService.readName(name);
    }
    @GetMapping("/read/phone")
    public List<PersonResponse> personReadPhone(@RequestParam String phone){
        return personReadService.readPhone(phone);
    }

}
