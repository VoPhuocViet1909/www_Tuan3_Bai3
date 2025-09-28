package iuh.fit.module3.servlet;

import iuh.fit.module3.dao.ProductDAO;
import iuh.fit.module3.model.CartBean;
import iuh.fit.module3.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    // Inject DataSource đã khai báo trong context.xml
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            productDAO = new ProductDAO(dataSource);
        } catch (Exception e) {
            throw new ServletException("Lỗi khi khởi tạo ProductDAO", e);
        }
    }

    // GET: chỉ hiển thị trang cart.jsp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    // POST: xử lý các thao tác add, update, remove, clear
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // Lấy giỏ hàng từ session, nếu chưa có thì tạo mới
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = productDAO.getProductById(id);
                cart.addProduct(p);

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("productId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateProductQuantity(id, quantity);

            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(req.getParameter("productId"));
                cart.removeProduct(id);

            } else if ("clear".equals(action)) {
                cart.clear();
            }

        } catch (Exception e) {
            throw new ServletException("Lỗi xử lý giỏ hàng", e);
        }

        // Sau khi xử lý xong, redirect về /cart để tránh lỗi F5 lặp lại POST
        resp.sendRedirect("cart");
    }
}
