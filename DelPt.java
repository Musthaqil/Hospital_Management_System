import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/DelPt")

public class DelPt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public DelPt() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		int aptno = Integer.parseInt(request.getParameter("aptno"));
		String pname = request.getParameter("name");
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/ajphospital","root","mu$Thaqil09");
			System.out.println("Connected "+con);
			String sql = "DELETE FROM appointment where aptno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aptno);
			ps.executeUpdate();
			con.close();
			pw.println("Appointment for Mr/Mrs/Ms."+pname+" has been cancelled");
			pw.println("Appointment Number: "+aptno);
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}