package iuh.fit.bai2_lan1.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import iuh.fit.bai2_lan1.dao.AccountUtil;
import iuh.fit.bai2_lan1.model.Account;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;




@WebServlet("/RegisterForm")   // URL mapping: khi form submit đến /registerform thì servlet này xử lý
public class AccountRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountUtil accountUtil;

    // Inject DataSource đã cấu hình trong context.xml (kết nối DB pool)
    @Resource(name = "jdbc/storedb")
    private DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            // Khởi tạo DAO (AccountUtil) với datasource
            accountUtil = new AccountUtil(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Lấy dữ liệu từ form
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        int day = Integer.parseInt(req.getParameter("day"));
        int month = Integer.parseInt(req.getParameter("month"));
        int year = Integer.parseInt(req.getParameter("year"));

        // 2. Chuyển ngày tháng năm -> java.sql.Date
        LocalDate localDate = LocalDate.of(year, month, day);
        java.sql.Date dob = java.sql.Date.valueOf(localDate);

        String gder = req.getParameter("gender");

        // 3. Tạo đối tượng Account
        Account account = new Account(firstname, lastname, email, password, dob, gder);

        System.out.println(account);

        try {
            // 4. Gọi DAO để lưu vào DB
            accountUtil.addAccount(account);

            // 5. Lấy danh sách account sau khi thêm mới
            List<Account> accounts = accountUtil.getAccounts();

            // 6. Đẩy dữ liệu sang JSP để hiển thị
            req.setAttribute("accounts", accounts);
            RequestDispatcher rd = req.getRequestDispatcher("account.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Tạm để trống (sau này có thể dùng để hiển thị form đăng ký)
    }
}
