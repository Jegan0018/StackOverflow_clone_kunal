package com.stackoverflow.clone.entity;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 1500)
    private String title;

    @Column(name = "problem", length = 1500)
    private String problem;

    @Column(name = "excepted_solution", length = 1500)
    private String exceptedSolution;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private Collection<Answer> answers;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        voteCount=0;
        createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @Transient
    private Long verifiedCount;

    public Question() {
    }

//    public Question(String title, String problem, String exceptedSolution,
//                    Set<Tag> tags, Timestamp createdAt, Timestamp updatedAt,
//                    ) {
//        this.title = title;
//        this.problem = problem;
//        this.exceptedSolution = exceptedSolution;
//        this.tags = tags;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getExceptedSolution() {
        return exceptedSolution;
    }

    public void setExceptedSolution(String exceptedSolution) {
        this.exceptedSolution = exceptedSolution;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Long getVerifiedCount() {
        return verifiedCount;
    }

    public void setVerifiedCount(Long verifiedCount) {
        this.verifiedCount = verifiedCount;
    }
}
