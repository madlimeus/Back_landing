package osteam.backland.domain.person.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PersonResponse {
    private String name;
    private List<String> phoneList;
}

