import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/AddPt")

public class AddPt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public AddPt() {
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
			String sql = "INSERT INTO appointment VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aptno);
			ps.setString(2, name);
			ps.setLong(3, cont);
			ps.setString(4, date);
			ps.setString(5, reason);
			ps.setInt(6, did);
			ps.setInt(7, rid);
			ps.executeUpdate();
			con.close();
			pw.println("Appointment created for patient Mr/Mrs/Ms."+name+" on "+date);
			pw.println("Appointment Number: "+aptno);
			pw.println("Consulting Doctor: "+dname);
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}