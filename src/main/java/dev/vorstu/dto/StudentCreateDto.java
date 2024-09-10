package dev.vorstu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StudentCreateDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("group")
    private String group;
    @JsonProperty("debt")
    private String debt;
    @JsonProperty("coments")
    private String coments;
}
