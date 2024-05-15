package sample.mvc;

import dbcontext.ConnectDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartController extends HttpServlet {

    private final String homePage = "index.jsp";
    private final String showPage = "show.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        try {
            if (action.equals("AddMore")) {
                RequestDispatcher rd = request.getRequestDispatcher(homePage);
                rd.forward(request, response);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("Add to Cart")) {
            HttpSession session = request.getSession();
            CartBean shop = (CartBean) session.getAttribute("SHOP");
            if (shop == null) {
                shop = new CartBean();
            }
            String title = request.getParameter("cboBook");
            BookDTO book = new BookDTO(title);
            shop.addBook(book);
            session.setAttribute("SHOP", shop);
            RequestDispatcher rd = request.getRequestDispatcher(homePage);
            rd.forward(request, response);
        } else if (action.equals("View Cart")) {
            RequestDispatcher rd = request.getRequestDispatcher(showPage);
            rd.forward(request, response);
        } else if (action.equals("AddMore")) {
            RequestDispatcher rd = request.getRequestDispatcher(homePage);
            rd.forward(request, response);
        } else if (action.equals("Remove")) {
            String[] list = request.getParameterValues("rmv");
            if (list != null) {
                HttpSession session = request.getSession();
                if (session != null) {
                    CartBean shop = (CartBean) session.getAttribute("SHOP");
                    if (shop != null) {
                        for (int i = 0; i < list.length; i++) {
                            shop.removeBook(list[i]);
                        }
                    }
                }
            }

            String url = "CartController?action=View Cart";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if (action.equals("AddToDB")) {
            HttpSession session = request.getSession();
            CartBean shop = (CartBean) session.getAttribute("SHOP");
            if (shop == null) {
                shop = new CartBean();
            }
            ConnectDB db = ConnectDB.getInstance();
            Connection conn = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            Collection<BookDTO> cartBook = shop.values();
            for (BookDTO bk : cartBook) {
                try {
                    conn = db.openConnection();
                    String query = "INSERT INTO BookDTO (title, quantity)\n"
                            + "VALUES (?, ?)";
                    statement = conn.prepareStatement(query);
                    statement.setString(1, bk.getTitle());
                    statement.setInt(2, bk.getQuantity());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(homePage);
            rd.forward(request, response);
            session.removeAttribute("SHOP");
        }
    }
}
