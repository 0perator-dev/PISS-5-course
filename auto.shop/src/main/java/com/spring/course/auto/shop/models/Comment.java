package com.spring.course.auto.shop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id")
    @OrderBy("id DESC")
    private User user;

    @ManyToOne
    @JoinColumn(columnDefinition = "announcement_id")
    @OrderBy("id DESC")
    private Announcement announcement;

    @Column(name = "message")
    private String message;
}
