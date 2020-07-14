package me.groupFour.data;
//Bean for storing reviews
public class Review {
    String Content; //Stores the content behind the review
    String Title; //Stores the title of the review
//getters and setters for content and title.
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
