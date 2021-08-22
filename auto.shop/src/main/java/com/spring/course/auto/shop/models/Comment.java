package com.spring.course.auto.shop.models;

import com.spring.course.auto.shop.models.ids.CommentId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @EmbeddedId
    private CommentId id = new CommentId();

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("userId")
    @OrderBy("id DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("announcementId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("id DESC")
    private Announcement announcement;

    @Column(name = "message")
    private String message;

    public Comment(CommentId id, User user, Announcement announcement, String message) {
        this.id = id;
        this.user = user;
        this.announcement = announcement;
        this.message = message;
    }

    public Comment() {
    }

    public CommentId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public String getMessage() {
        return message;
    }

    public void setId(CommentId id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
