/*
 * upload.java
 *
 * Created on 2007年5月26日, 下午3:00
 */

package inc;

import java.io.*;
import java.net.*;
import java.sql.*;
import javax.sql.*;
import inc.condb;
import javax.servlet.*;
import javax.servlet.http.*;

public class upload
    extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");
    if (username == null) {
      response.sendRedirect("index.html");
      return;
    }
    try {
      ResultSet rs = null;
      condb conn = new condb();
      out.println("\r\n");
      out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.println("<head>\r\n");
      out.println(
          "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\r\n");

      out.println("<style type=\"text/css\">\r\n");
      out.println("<!--\r\n");
      out.println("body {\r\n");
      out.println("\tmargin-left: 0px;\r\n");
      out.println("\tmargin-top: 0px;\r\n");
      out.println("}\r\n");
      out.println("-->\r\n");
      out.println("</style></head>\r\n");
      out.println("\r\n");
      out.println("<body>\r\n");
      out.println("<form action=\"Uploadfile.do\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.println("<input name=\"files\" type=\"file\" /><br />\r\n");
      out.println("<input type=\"submit\" value=\"上传\" />\r\n");
      out.println("</form>\r\n");
      out.println("</body>\r\n");
      out.println("</html>\r\n");
      out.println("\r\n");

    }
    catch (Exception e) {

    }

  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);
  }
}
