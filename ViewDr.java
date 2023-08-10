import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/ViewDr")

public class ViewDr extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ViewDr() {
		super();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/ajphospital","root","mu$Thaqil09");
			System.out.println("Connected "+con);
			String sql = "SELECT * FROM doctor";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			pw.println("LIST OF DOCTORS");
			pw.println();
			while(rs.next()) {
				pw.println("ID: "+rs.getInt(1));
				pw.println("Name: "+rs.getString(2));
				pw.println("Contact: "+rs.getLong(3));
				pw.println("LICENSE: "+rs.getString(4));
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