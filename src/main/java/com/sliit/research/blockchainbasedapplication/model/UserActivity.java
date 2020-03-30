package com.sliit.research.blockchainbasedapplication.model;

import com.sliit.research.blockchainbasedapplication.constants.LogTypes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LogTypes logType;
    private Date date;

    private Long userId;
    private Long productId;
    private Long reviewId;

    public UserActivity() {
    }

    public UserActivity(LogTypes logType, Date date, Long userId, Long productId, Long reviewId) {
        this.logType = logType;
        this.date = date;
        this.userId = userId;
        this.productId = productId;
        this.reviewId = reviewId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogTypes getLogType() {
        return logType;
    }

    public void setLogType(LogTypes logType) {
        this.logType = logType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
}
