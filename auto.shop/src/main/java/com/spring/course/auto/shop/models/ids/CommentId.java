package com.spring.course.auto.shop.models.ids;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CommentId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long announcementId;
}
