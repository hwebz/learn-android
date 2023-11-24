package com.hado.ecommercehealthyapp.Domains;

public class Category {
    private String imagePath;
    private String title;

    public Category(String imagePath, String title) {
        this.imagePath = imagePath;
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
