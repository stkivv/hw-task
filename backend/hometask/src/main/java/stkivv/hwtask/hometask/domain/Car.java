package stkivv.hwtask.hometask.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Car extends BaseEntity {
    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String numberPlate;

    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "id", nullable = false)
    private User user;
}
