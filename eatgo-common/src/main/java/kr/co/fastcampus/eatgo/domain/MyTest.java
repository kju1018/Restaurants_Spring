package kr.co.fastcampus.eatgo.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyTest {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @Setter
    @NotEmpty
    private String name;

    @Setter
    @NotNull
    private Long level;
}
