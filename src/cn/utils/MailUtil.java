package cn.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    public static String myEmailAccount = "geng584521@163.com";
    public static String myEmailPassword = "gyg06103234";
    public static String myEmailSMTPHost = "smtp.163.com";
    String receiveMailAccount = "1713143151@qq.com";

    public static void sendActiveMail(String receiveMailAccount,String mailActiveCode) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, mailActiveCode);

        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 创建邮件
     * @param session
     * @param sendMail
     * @param receiveMail
     * @param mailActiveCode
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String mailActiveCode) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "G_Blue_CN", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        message.setSubject("用户激活", "UTF-8");
        String activeUrl="http://localhost:8080/Blog_war_exploded/activeUserServlet?code="+mailActiveCode;
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("尊敬的用户，您好！这里是Blue的博客，请点击激活链接完成邮箱激活<a href="
                +activeUrl+" rel=\"nofollow\">"+activeUrl+
                "</a>", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}
