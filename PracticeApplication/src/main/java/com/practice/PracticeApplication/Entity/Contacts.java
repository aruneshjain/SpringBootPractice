package com.practice.PracticeApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Contacts {
    @Column( name = "personal")
    private  String personal;

    @Column( name = "secondary")
    private  String secondary;

    @Column( name = "emergency")
    private  String emergency;

}
