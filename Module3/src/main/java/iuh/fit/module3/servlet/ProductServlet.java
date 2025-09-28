package iuh.fit.module3.servlet;

import iuh.fit.module3.dao.ProductDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init(){
        productDAO = new ProductDAO(dataSource);

    }
    @Override
    protected  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");

        if (idstr != null) {
            int id = Integer.parseInt(idstr);
            req.setAttribute("product", productDAO.getProductById(id));
            req.getRequestDispatcher("/product-detail.jsp").forward(req, resp);
        } else {
            req.setAttribute("products", productDAO.getAllProducts());
            req.getRequestDispatcher("/product-list.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // doGet(req, resp);
    }

}
