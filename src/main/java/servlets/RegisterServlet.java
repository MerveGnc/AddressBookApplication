package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccess.InMemoryUserDao;
import entity.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kullanıcıyı kaydet
        boolean saved = saveUser(username, password);

        if (saved) {
            // Kayıt başarılıysa, oturumu oluştur ve kullanıcıyı kaydet
            HttpSession session = request.getSession();
            session.setAttribute("user", new User(username, password));

            response.sendRedirect("kayit.xhtml");
        } else {
            request.setAttribute("error", "Kayıt işlemi başarısız!");
            request.getRequestDispatcher("kayithata.xhtml").forward(request, response);
        }
    }

    private boolean saveUser(String username, String password) {
        // InMemoryUserDAO kullanarak kullanıcıyı kaydet
        InMemoryUserDao userDao = InMemoryUserDao.getInstance();
        User user = new User(username, password);

        // Kullanıcı adının kullanılabilir olup olmadığını kontrol et
        if (!userDao.isUsernameAvailable(username)) {
            return false; // Kullanıcı adı zaten kullanımda, kaydetme işlemi başarısız
        }

        return userDao.saveUser(user);
    }
}
