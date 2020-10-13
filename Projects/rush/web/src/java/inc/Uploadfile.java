package inc;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;

import java.util.regex.*;
import java.util.*;

public class Uploadfile
    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html;charset=utf-8";

  public Uploadfile() {
    super();
  }

  public void destroy() {
    super.destroy();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    String id = (String) session.getAttribute("id");
    String username = (String) session.getAttribute("username");
    ServletContext sc = getServletContext();
    String uploadPath = sc.getRealPath("/uploadFiles");
    String tempPath = sc.getRealPath("/uploadFiles");
    DiskFileUpload dfu = new DiskFileUpload();
    dfu.setSizeMax(100 * 1024 * 1024);
    dfu.setSizeThreshold(4096); //����������ڴ��д��4096���ֽ�
    dfu.setRepositoryPath(tempPath); //���ڴ��д�ŵ��־ݳ���setSizeThreshold()���趨��ֵʱ,
    if (username == null) {
      response.sendRedirect("index.html");
      return;
    }

    try {
      List fileitems = dfu.parseRequest(request);
      Iterator it = fileitems.iterator();
      String regExp = ".+\\\\(.+)$"; //����ƥ��,ȥ��·��ȡ�ļ���.
      //String canSuffix = ".gif.jpg.jpeg.png"; //���ϴ����ļ���׺��
      String errortype[] = {".exe", ".com", ".cgi", ".asp", ".dll"};
      Pattern p = Pattern.compile(regExp);

      while (it.hasNext()) {
        FileItem fileitem = (FileItem) it.next();
//   ���Բ����ļ����Ԫ��.
        if (!fileitem.isFormField()) {
          String name = fileitem.getName();
          long size = fileitem.getSize();
          if ("".equals(name) || size == 0) {
            continue;
          }
          System.out.println(name);
          Matcher m = p.matcher(name);
          boolean result = m.find();
          if (result) {
           for (int i = 0; i < errortype.length; i++) {
                  if (m.group(1).endsWith(errortype[i])) {
                      throw new IOException(
                              "�ļ����ʹ��� <a href=\"upload.do\">����</a>");
                  }
             }
          }
          try {
            String filename = new Date().getTime() + m.group(1);
            fileitem.write(new File(uploadPath + "\\" +
                                    filename));
            session.setAttribute("file", filename);
            out.println(m.group(1) + "�ϴ��ɹ�");
          }
          catch (Exception e) {
            throw new IOException(
                "�ļ��ϴ�ʧ�� <a href=\"upload.do\">����</a>");
          }
        }
        else {
          throw new IOException(
              "�ļ��ϴ�ʧ�� <a href=\"upload.do\">����</a>");
        }
      }
   
      }
    
    catch (Exception e) {
      out.println("<font size=2>" + e.getMessage() + "</font>");
      return;
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      ServletException, IOException {
    doGet(req, resp);
  }

}