/**
 * 
 */
package com.ringencorp.ezrtt.backend.apis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.GeoPt;
import com.ringencorp.ezrtt.backend.db.DatabaseWrapper;
import com.ringencorp.ezrtt.backend.model.entities.ModelEmper;

/**
 * @author Devanshu
 *
 */
public class RegEmperServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9174122475450116111L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		// resp.setStatus(200);
		resp.sendRedirect("index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inside reger empe");
		ModelEmper emper = new ModelEmper(req.getParameter("user"), req.getParameter("name"),
				req.getParameter("address"), new GeoPt(0, 0), "dummy conty",
				Long.parseLong(req.getParameter("headcount")), Integer.parseInt(req.getParameter("wkdaystart")),
				Integer.parseInt(req.getParameter("wkdayend")));

		emper.setActive(true);

		DatabaseWrapper db = new DatabaseWrapper();

		db.insertEmper(emper);
		resp.setStatus(200);
		resp.sendRedirect("/index.jsp");
	}

}
