package com.spring.course.auto.shop.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
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
