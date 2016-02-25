/**
 * 
 */
package com.ringencorp.ezrtt.backend.apis;

import com.ringencorp.ezrtt.backend.db.DatabaseWrapper;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmpee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Devanshu
 *
 */
public class AddEmployeeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9174122475450116111L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		// resp.setStatus(200);
		resp.sendRedirect("/addempee");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mobile = req.getParameter("mobile");
		String emper = req.getParameter("emper");
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String email = req.getParameter("email");
		// req.getParameter("mobile");
		String intime = req.getParameter("intime") + ":00";
		String outtime = req.getParameter("outtime") + ":00";

		ModelEmpee empee = new ModelEmpee(mobile, emper, fname, lname, email, mobile, intime, outtime);

		empee.setActive(false);

		DatabaseWrapper db = new DatabaseWrapper();

		db.insertEmpee(empee);
		resp.setStatus(200);
		resp.sendRedirect("closewindow.html");
	}
}
