package com.comunal.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateRequestDTO implements Serializable {

    @Min(1)
    @Max(6)
    private long typeId;

    @Min(value = 1)
    private int amount;

    private String description;

    @NotNull
    private Date date;

    public long getTypeId() {
        return typeId;
    }

    public CreateRequestDTO setTypeId(long type) {
        this.typeId = type;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public CreateRequestDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateRequestDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public CreateRequestDTO setDate(Date date) {
        this.date = date;
        return this;
    }

    public CreateRequestDTO setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(date);
        return this;
    }
}
