package com.codeseasy.loginui.ClassModel;

public class Book {
    private int id;
    private String bookName;
    private byte[] Image;
    private int price;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Book(int id, String bookName, byte[] image, int price) {
        this.id = id;
        this.bookName = bookName;
        Image = image;
        this.price = price;
    }
}