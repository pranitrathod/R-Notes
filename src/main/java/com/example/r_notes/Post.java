package com.example.r_notes;

public class Post {
    String Details;
    String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Post() {//this is primary constructor means there no argument and this is importent must be declare
    }

    @Override
    public String toString() {
        return "Post{" +
                "Details='" + Details + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }

    public String getDetails() {
        return Details;
    }



    public void setDetails(String details) {
        Details = details;
    }
}
