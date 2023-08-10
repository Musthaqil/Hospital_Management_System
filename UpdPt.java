import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/UpdPt")

public class UpdPt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public UpdPt() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		int aptno = Integer.parseInt(request.getParameter("aptno"));
		String name = request.getParameter("name");
		long cont = Long.parseLong(request.getParameter("cont"));
		String date = request.getParameter("date");
		String reason = request.getParameter("reason");
		int did = Integer.parseInt(request.getParameter("did"));
		String dname = request.getParameter("dname");
		int rid = Integer.parseInt(request.getParameter("rid"));
		String rname = request.getParameter("rname");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/ajphospital","root","mu$Thaqil09");
			System.out.println("Connected "+con);
			String sql = "UPDATE appointment set pname=?, pcont=?, aptdate=?, reason=?, did=?, rid=? where aptno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setLong(2, cont);
			ps.setString(3, date);
			ps.setString(4, reason);
			ps.setInt(5,did);
			ps.setInt(6, rid);
			ps.setInt(7, aptno);
			ps.executeUpdate();
			con.close();
			pw.println("Appointment details have updated Successfully for the Appointment Number: "+aptno);
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}