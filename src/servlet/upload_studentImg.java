package servlet;

import dao.StudentD;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@WebServlet("/upload_studentImg")
public class upload_studentImg extends HttpServlet {
    StudentD studentD=new StudentD();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("id");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // Check that we have a file upload request
        boolean isMultipart = JakartaServletDiskFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            out.println("No file uploaded");
            return;
        }

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        // Set factory constraints

        // Create a new file upload handler
        JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);

        try {
            // Parse the request
            List<DiskFileItem> items = upload.parseRequest(request);
            String id="";
            String fileName = "";
            for (DiskFileItem item : items) {
                // Process form fields

                if (item.isFormField()) {
//                    String fieldName = item.getFieldName();
//                    String fieldValue = item.getString();
                    if(item.getFieldName().equals("id")) {
                        id=item.getString();
                    }
                    // Handle form field
                } else {
                    // Process uploaded file
                    // Here you can handle the uploaded file
                    // For example, you can save it to a directory
                    // String fileName = item.getName();
                    // File uploadedFile = new File("/path/to/save/" + fileName);
                    // item.write(uploadedFile);
                    fileName =item.getName();
                    if(fileName != null){
                        fileName = new Date().getTime() + "." + FilenameUtils.getExtension(fileName);
                    }
                    item.write(Path.of(request.getServletContext().getRealPath("/userImg") + "//" + fileName));
                    out.print("<script>alert(\"上传成功!\");window.location.href='student/personal.jsp';</script>");
//                    studentD.updateImg(id,"stu/userImg"+"/"+fileName);
                }
            }
            studentD.updateImg(id,"stu/userImg"+"/"+fileName);
            response.sendRedirect(request.getContextPath() + "/student/personal.jsp");
        } catch (Exception e) {
            out.print(e);
        }
    }
}