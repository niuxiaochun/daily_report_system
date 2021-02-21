package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GoodDetails;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsGoodshowServlet
 */
@WebServlet("/reports/goodshow")
public class ReportsGoodshowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsGoodshowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

         int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<GoodDetails> gooddetails = em.createNamedQuery("getALLGoodDetail", GoodDetails.class)
                                  .setParameter("report", r)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long good_count = (long)em.createNamedQuery("getGoodDetailCount", Long.class)
                                     .setParameter("report", r)
                                     .getSingleResult();

        em.close();

        request.setAttribute("gooddetails", gooddetails);
        request.setAttribute("good_count", good_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/goodShow.jsp");
        rd.forward(request, response);
    }
}