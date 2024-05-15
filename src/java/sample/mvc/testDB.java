package sample.mvc;

public class testDB {

    public static void main(String[] args) {
        CartBean ls=new CartBean();
        BookDTO b=new BookDTO("Book4");
        for (int i = 0; i < 3; i++) {
           ls.addBook(b);
        }
        System.out.println(((BookDTO)ls.get("Book4")).getQuantity());
    }
}