import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/ViewPt")

public class ViewPt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ViewPt() {
		super();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/ajphospital","root","mu$Thaqil09");
			System.out.println("Connected "+con);
			String sql = "SELECT * FROM appointment";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			pw.println("LIST OF APPOINTMENTS/PATIENTS");
			pw.println();
			while(rs.next()) {
				pw.println("APPOINTMENT NUMBER: "+rs.getInt(1));
				pw.println("Patient Name: "+rs.getString(2));
				pw.println("Contact: "+rs.getLong(3));
				pw.println("Appointment Date: "+rs.getString(4));
				pw.println("Reason: "+rs.getString(5));
				pw.println("Doctor's ID: "+rs.getInt(6));
				pw.println("Receptionist ID: "+rs.getInt(7));
				pw.println();
			}
			con.close();

		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}