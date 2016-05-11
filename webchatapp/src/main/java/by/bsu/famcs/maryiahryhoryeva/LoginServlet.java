package by.bsu.famcs.maryiahryhoryeva;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private User[] users = new User[2];

    public LoginServlet() {
        super();
        users[0] = new User("User1", "user1");
        users[1] = new User("User2", "user2");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (userExists(username, password)) {
            resp.sendRedirect("/index.html");
        } else {
            resp.sendRedirect("/login.html");
        }
    }

    private boolean userExists(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
