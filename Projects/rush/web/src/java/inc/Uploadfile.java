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
    dfu.setSizeThreshold(4096); //最多允许在内存中存放4096个字节
    dfu.setRepositoryPath(tempPath); //当内存中存放的字据超过setSizeThreshold()所设定的值时,
    if (username == null) {
      response.sendRedirect("index.html");
      return;
    }

    try {
      List fileitems = dfu.parseRequest(request);
      Iterator it = fileitems.iterator();
      String regExp = ".+\\\\(.+)$"; //正则匹配,去掉路径取文件名.
      //String canSuffix = ".gif.jpg.jpeg.png"; //可上传的文件后缀名
      String errortype[] = {".exe", ".com", ".cgi", ".asp", ".dll"};
      Pattern p = Pattern.compile(regExp);

      while (it.hasNext()) {
        FileItem fileitem = (FileItem) it.next();
//   忽略不是文件域的元素.
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
                              "文件类型错误 <a href=\"upload.do\">返回</a>");
                  }
             }
          }
          try {
            String filename = new Date().getTime() + m.group(1);
            fileitem.write(new File(uploadPath + "\\" +
                                    filename));
            session.setAttribute("file", filename);
            out.println(m.group(1) + "上传成功");
          }
          catch (Exception e) {
            throw new IOException(
                "文件上传失败 <a href=\"upload.do\">返回</a>");
          }
        }
        else {
          throw new IOException(
              "文件上传失败 <a href=\"upload.do\">返回</a>");
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
