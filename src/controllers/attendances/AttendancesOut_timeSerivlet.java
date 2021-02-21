package controllers.attendances;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import models.validators.AttendanceValidator;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesOut_timeSerivlet
 */
@WebServlet("/attendances/out_time")
public class AttendancesOut_timeSerivlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesOut_timeSerivlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = ((Employee)request.getSession().getAttribute("login_employee"));



        //1, 本日の日付を取得する
        Date attendance_date = new Date(System.currentTimeMillis());

        //2, 取得した本日の日付に該当するレコードを取得する
        Attendance attendances = (Attendance)em.createNamedQuery("getloginEmployeeAttendance_date", Attendance.class)
                .setParameter("employee", login_employee)
                .setParameter("attendance_date", attendance_date )
                .getSingleResult();


      //3, 2番のレコードが存在しなければ、エラーにする（出勤していないのに退勤しようとしていているため）
        List<String> errors = AttendanceValidator.validate(attendances);
        if(errors.size() > 0) {
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("attendance", attendances);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/index.jsp");
            rd.forward(request, response);
        } else {
          //4, 2番のレコードが存在していたら、そのレコードの退勤時刻を更新する
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            attendances.setOut_time(currentTime);
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "お疲れ様です、退勤しました。");

            request.getSession().removeAttribute("Attendance_id");

            response.sendRedirect(request.getContextPath() + "/attendances/index");
        }
    }

}
