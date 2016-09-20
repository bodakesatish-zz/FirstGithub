package com.hvdesai.register;

/**
 * Created by SATISH BODAKE on 9/17/2016.
 */
public class DTOUser {

    String name = "";

    public DTOUser(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
