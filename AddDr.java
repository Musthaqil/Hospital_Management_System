import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/AddDr")

public class AddDr extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public AddDr() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		long cont = Long.parseLong(request.getParameter("cont"));
		String lic = request.getParameter("lic");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/ajphospital","root","mu$Thaqil09");
			System.out.println("Connected "+con);
			String sql = "INSERT INTO doctor VALUES(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setLong(3, cont);
			ps.setString(4, lic);
			ps.executeUpdate();
			con.close();
			pw.println("Dr."+name+"'s details added Successfully");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}