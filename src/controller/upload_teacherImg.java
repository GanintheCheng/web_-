package controller;

import dao.impl.TeacherDImpl;
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
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@WebServlet("/upload_teacherImg")
public class upload_teacherImg extends HttpServlet {
    TeacherDImpl teacherDImpl = new TeacherDImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        boolean isMultipart = JakartaServletDiskFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            out.println("No file uploaded");
            return;
        }
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);

        try {
            List<DiskFileItem> items = upload.parseRequest(request);
            String id = "";
            String fileName = "";
            for (DiskFileItem item : items) {

                if (item.isFormField()) {
                    if (item.getFieldName().equals("id")) {
                        id = item.getString();
                    }
                } else {
                    fileName = item.getName();
                    if (fileName != null) {
                        fileName = new Date().getTime() + "." + FilenameUtils.getExtension(fileName);
                    }
                    item.write(Path.of(request.getServletContext().getRealPath("/userImg") + "//" + fileName));
                    out.print("<script>alert(\"上传成功!\");window.location.href='teacher/personal.jsp';</script>");
                }
            }
            teacherDImpl.updateImg(id, "stu/userImg" + "/" + fileName);
            response.sendRedirect(request.getContextPath() + "/teacher/personal.jsp");
        } catch (Exception e) {
            out.print(e);
        }
    }
}