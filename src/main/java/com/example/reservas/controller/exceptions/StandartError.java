package com.example.reservas.controller.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable{
    
    private Long timestemp;
    private Integer status;
    private String error;

    // Constructors
    public StandartError(){
    }

    public StandartError(Long timestemp, Integer status, String error) {
        this.timestemp = timestemp;
        this.status = status;
        this.error = error;
    }

    // Getters e Setters
    public Long getTimestemp() {
        return timestemp;
    }
    public void setTimestemp(Long timestemp) {
        this.timestemp = timestemp;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    

}
