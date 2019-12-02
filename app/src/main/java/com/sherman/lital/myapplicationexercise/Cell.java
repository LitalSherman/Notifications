package com.sherman.lital.myapplicationexercise;

/**
 * Created by Vlad on 12/2/2019.
 */

public class Cell {

    private String Name;
    private String PhoneNum;
    private String Address;
    private int Age;
    private String Image;

    public Cell(String name, String phoneNum, String address, int age, String image) {
        Name = name;
        PhoneNum = phoneNum;
        Address = address;
        Age = age;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
