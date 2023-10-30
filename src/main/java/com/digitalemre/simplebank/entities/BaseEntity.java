package com.digitalemre.simplebank.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public  class  BaseEntity {
    private Date date;


    @PrePersist
    public void onCreate(){
        this.date=new Date();
    }
}
