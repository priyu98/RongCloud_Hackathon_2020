/*
 * logout.java
 *
 * Created on 2007��5��31��, ����9:23
 */

package inc;

import java.io.*;
import java.net.*;
import java.sql.*;
import javax.sql.*;
import inc.condb;
import javax.servlet.*;
import javax.servlet.http.*;

public class logout
    extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();

    session.invalidate();

  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);
  }
}
