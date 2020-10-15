/*
 * online.java 查看所有在线人员
 *
 * Created on 2007年5月23日, 下午7:15
 */

package admin;

import java.io.*;
import java.net.*;
import java.sql.*;
import javax.sql.*;
import inc.condb;
import javax.servlet.*;
import javax.servlet.http.*;

public class online
    extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");
    if (username == null) {
      response.sendRedirect("../index.html");
      return;
    }
    try {
      ResultSet rs = null;
      condb conn = new condb();
      String sql = "select * from userinfo";

      rs = conn.executeQuery(sql);

      out.println("\r\n");
      out.println("<style type=\"text/css\">\r\n");
      out.println("<!--\r\n");
      out.println("body,td,th {\r\n");
      out.println("\tfont-size: 12px;\r\n");
      out.println("}\r\n");
      out.println("-->\r\n");
      out.println("</style><body>\r\n");
      out.println(
          "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.println("  <tr>\r\n");
      out.println("    <td width=\"50\" align=\"center\">ID</td>\r\n");
      out.println("    <td width=\"100\" align=\"center\">用户名</td>\r\n");
      out.println("    <td width=\"100\" align=\"center\">姓名</td>\r\n");
      out.println("   \r\n");
      out.println("    <td width=\"80\" align=\"center\">接收信息数</td>\r\n");
      out.println("    <td width=\"80\" align=\"center\">发送信息数</td>\r\n");
      // out.println("    <td align=\"center\">统计</td>\r\n");
      out.println("  </tr>\r\n");
      int i = 0;
      while (rs.next()) {
        out.println("  <tr>\r\n");
        out.println("    <td align=\"center\">" + (++i) + "</td>\r\n");
        out.println("    <td align=\"center\">" + rs.getString("username") +
                    "</td>\r\n");
        out.println("    <td align=\"center\">" + rs.getString("zsname") +
                    "</td>\r\n");
        out.println("    <td align=\"center\">" + rs.getString("innum") +
                    "</td>\r\n");
        out.println("    <td align=\"center\">" + rs.getString("outnum") +
                    "</td>\r\n");
        /* int innum=0;
         innum=rs.getInt(innum);
         int outnum=0;
         outnum=rs.getInt(outnum);
         double sumn=0.0;
         sumn=innum*outnum;
         */
        out.println("    <td></td>\r\n");
        out.println("    <td>&nbsp;</td>\r\n");
        out.println("    <td>&nbsp;</td>\r\n");
        out.println("  </tr>\r\n");
      }
      out.println("</table>\r\n");
      out.println("</body>");
    }
    catch (Exception e) {

    }

  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);
  }
}
