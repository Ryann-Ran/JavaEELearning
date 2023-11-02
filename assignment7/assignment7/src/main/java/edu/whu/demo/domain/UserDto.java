package edu.whu.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto extends User{
    private List<Role> roles = new java.util.ArrayList<>();
}
