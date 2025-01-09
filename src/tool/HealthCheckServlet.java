package tool;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/health")
public class HealthCheckServlet extends javax.servlet.http.HttpServlet{

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req,javax.servlet.http.HttpServletResponse resp)
		throws ServletException, IOException{


	}

}
