package com.comunal.model;

import com.comunal.model.enums.RequestStatus;
import com.comunal.model.enums.WorkType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "request")
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private WorkType type;

    @ManyToOne
    private User user;

    private Date date;

    private int amount;

    private String description;

    @ManyToOne
    private Team team;

    private RequestStatus status = RequestStatus.NEW;

    public Long getId() {
        return id;
    }

    public Request setId(Long id) {
        this.id = id;
        return this;
    }

    public WorkType getType() {
        return type;
    }

    public Request setType(WorkType type) {
        this.type = type;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Request setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Request setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Request setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Request setDescription(String description) {
        this.description = description;
        return this;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public Request setStatus(RequestStatus status) {
        this.status = status;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public Request setTeam(Team team) {
        this.team = team;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", type=" + type +
                ", user=" + user +
                ", date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", team=" + team +
                ", status=" + status +
                '}';
    }
}


