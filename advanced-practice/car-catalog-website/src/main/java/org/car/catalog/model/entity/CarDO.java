package org.car.catalog.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CAR_CATALOG")
@Getter
@Setter
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carSeq")
    //initialValue = 7 only for testing
    @SequenceGenerator(name = "carSeq", sequenceName = "car_seq", allocationSize = 1, initialValue = 7)
    @Column(name = "ID", nullable = false, columnDefinition = "NUMBER(3, 0)")
    private Integer id;

    @Column(name = "MODEL", nullable = false, columnDefinition = "VARCHAR2(20 CHAR)")
    private String model;

    @Column(name = "MAKE", nullable = false, columnDefinition = "VARCHAR2(20 CHAR)")
    private String make;

    @Column(name = "PHOTO", columnDefinition = "VARCHAR2(30 CHAR)")
    private String photo;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR2(800 CHAR)")
    private String description;

    @Column(name = "PRICE", nullable = false, columnDefinition = "NUMBER(7, 0)")
    private Integer price;

    @Column(name = "AMOUNT", nullable = false, columnDefinition = "NUMBER(3, 0)")
    private Integer amount;
}
