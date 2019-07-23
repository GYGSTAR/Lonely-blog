package cn.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
        //创建图片
        int width = 100;
        int height = 35;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //美化
        Color[] colors = {Color.BLUE,Color.CYAN,Color.GREEN,Color.YELLOW,Color.WHITE};
        Random rand = new Random();
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);
        //填写验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder str_checkCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = rand.nextInt(str.length());
            char ch = str.charAt(index);
            str_checkCode.append(ch);
            g.setColor(colors[rand.nextInt(colors.length)]);
            //设置字体的小大
            g.setFont(new Font("微软雅黑",Font.BOLD,24));
            g.drawString(ch+"",(i+1)*width/5-g.getFont().getSize()/4,(height+g.getFont().getSize()/2)/2);
        }
        //生成验证码，session共享
        String checkCode_session = str_checkCode.toString();
        request.getSession().setAttribute("checkCode_session", checkCode_session);
        //画干扰线
        for (int i = 0; i < 8; i++) {
            int color = rand.nextInt(colors.length);
            int x1 = rand.nextInt(width-1);
            int x2 = rand.nextInt(width-1);
            int y1 = rand.nextInt(height-1);
            int y2 = rand.nextInt(height-1);
            g.setColor(colors[color]);
            g.drawLine(x1,x2,y1,y2);
        }
        //输出图片
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
