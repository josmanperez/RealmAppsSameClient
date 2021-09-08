package com.example.realmappsameclient.model;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PersonB extends RealmObject {

    @PrimaryKey
    @Required
    private ObjectId _id;

    @Required
    private String _partition;

    private String age;

    private String firtsName;

    private String lastName;

    private String createdWhere;

    private String Timestamp;

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String get_partition() {
        return _partition;
    }

    public void set_partition(String _partition) {
        this._partition = _partition;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedWhere() {
        return createdWhere;
    }

    public void setCreatedWhere(String createdWhere) {
        this.createdWhere = createdWhere;
    }
}
