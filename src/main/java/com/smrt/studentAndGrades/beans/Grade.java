package com.smrt.studentAndGrades.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float score;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    public long getId() {
        return id;
    }

    public float getScore() {
        return score;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "(" + topic + ", " + score + ")";
    }
}
