package com.spring.course.auto.shop.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "announcements")
@Setter
@Getter
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 60)
    private String title;

    @Column
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "announcement")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy = "announcement")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Image> images;
}
