package dev.vorstu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.vorstu.entities.Password;
import dev.vorstu.entities.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserShow {

    @JsonProperty("Id")
    private Long Id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("password")
    private Password password;

    @JsonProperty("enable")
    private boolean enable;

}
