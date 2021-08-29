package com.spring.course.auto.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.course.auto.shop.models.enums.ERole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 60)
    private ERole name;
}
