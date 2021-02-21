package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.GoodDetails;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsGoodServlet
 */
@WebServlet("/reports/good")
public class ReportsGoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());


        GoodDetails gd = new GoodDetails();
        gd.setEmployee((Employee) request.getSession().getAttribute("login_employee"));
        gd.setReport(r);
        gd.setCreated_at(currentTime);

        em.getTransaction().begin();
        em.persist(gd);
        em.getTransaction().commit();

        em.close();

        request.getSession().setAttribute("flush", "いいねしました！");

        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}