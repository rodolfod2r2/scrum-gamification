package org.framework.rodolfo.freire.git.scrumgamification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.framework.rodolfo.freire.git.scrumgamification.observer.Subject;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Developer extends Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role;
    private int XP;
    private int level;

}
