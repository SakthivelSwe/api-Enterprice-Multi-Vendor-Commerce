package com.tvm.productEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Vendor
{
    @Id
    private long id;
    private String name;

    @OneToMany(mappedBy = "vendor")

    // Getters & Setters
    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }



    public Vendor()
    {

    }

    public Vendor(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
