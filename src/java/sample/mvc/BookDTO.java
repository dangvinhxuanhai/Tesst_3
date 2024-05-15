package sample.mvc;

import java.io.Serializable;

public class BookDTO implements Serializable{
    private String title;
    private int quantity=0;

    public BookDTO() {
    }

    public BookDTO(String title) {
        this.title = title;
        this.quantity=1;
    }

    public BookDTO(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookDTO{" + "title=" + title + ", quantity=" + quantity + '}';
    }
    
    
}