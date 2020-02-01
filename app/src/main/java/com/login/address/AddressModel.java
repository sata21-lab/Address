package com.login.address;

public class AddressModel {
    private String Name;
    private String Phone;
    private String type;
    private String Address;
    private String sqlite_id;

    public AddressModel(String sqlite_id,String Name, String Phone,String Address,String type) {
        this.sqlite_id=sqlite_id;
        this.Name=Name;
        this.Phone=Phone;
        this.type=type;
        this.Address=Address;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSqlite_id() {
        return sqlite_id;
    }

    public void setSqlite_id(String sqlite_id) {
        this.sqlite_id = sqlite_id;
    }
}

