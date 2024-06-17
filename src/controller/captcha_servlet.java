package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.imageio.ImageIO;

@WebServlet("/code")
public class captcha_servlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache");

        // 创建图像
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取画笔
        Graphics g = image.getGraphics();
        // 设定背景色
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, width, height);
        // 随机产生四位数字
        Random rnd = new Random();
        int randNum = rnd.nextInt(8999) + 1000;
        String randStr = String.valueOf(randNum);
        // 将验证码存入 session
        HttpSession session = request.getSession(true);
        session.setAttribute("randStr", randStr);
        // 显示到图像中
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.PLAIN, 20));
        g.drawString(randStr, 10, 17);
        // 随机产生100个干扰点
        for (int i = 0; i < 100; i++) {
            int x = rnd.nextInt(width);
            int y = rnd.nextInt(height);
            g.drawOval(x, y, 1, 1);
        }
        // 输出到页面
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }
}
