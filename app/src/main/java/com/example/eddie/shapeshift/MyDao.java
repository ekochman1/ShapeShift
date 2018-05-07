package com.example.eddie.shapeshift;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao{

    @Insert
    public void addUser(User user);

    @Query("select * from log")

    public List<User> getUser();

    @Delete
    public void deleteUser(User user);



}