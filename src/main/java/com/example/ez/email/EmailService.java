package com.example.ez.email;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    // ✅ Spring Boot auto-configures this from spring.mail.* properties
    private final JavaMailSender mailSender;

    // ✅ Read from spring.mail.username (same key Spring Boot uses)
    @Value("${spring.mail.username}")
    private String fromEmail;

    private static final String FROM_NAME = "EZ Construction";

    // ✅ @Async so email sending never blocks the HTTP response
    @Async
    public void sendEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            // true = multipart, true = html
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, FROM_NAME);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = isHtml

            mailSender.send(message);
            log.info("✅ Email sent successfully to: {}", to);

        } catch (Exception e) {
            log.error("❌ Failed to send email to: {} | Error: {}", to, e.getMessage(), e);
        }
    }

    // ═══════════════════════════════════════════════
    //  EMAIL TEMPLATES
    // ═══════════════════════════════════════════════

    public String buildSubmissionConfirmationEmail(String name) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>
            <body style="margin:0;padding:0;background-color:#f4f6f9;font-family:'Segoe UI',Tahoma,Geneva,Verdana,sans-serif;">
                <table width="100%%" cellpadding="0" cellspacing="0"
                    style="background-color:#f4f6f9;padding:40px 20px;">
                    <tr>
                        <td align="center">
                            <table width="600" cellpadding="0" cellspacing="0"
                                style="background-color:#ffffff;border-radius:16px;overflow:hidden;
                                       box-shadow:0 4px 24px rgba(0,0,0,0.08);">

                                <!-- Header -->
                                <tr>
                                    <td style="background:linear-gradient(135deg,#0d1f5c 0%%,#1a56e8 100%%);
                                               padding:40px 40px 30px;text-align:center;">
                                        <div style="width:56px;height:56px;background:rgba(255,255,255,0.15);
                                                    border-radius:14px;display:inline-block;
                                                    margin-bottom:16px;line-height:56px;">
                                            <span style="font-size:28px;">🏗️</span>
                                        </div>
                                        <h1 style="color:#ffffff;font-size:24px;font-weight:700;
                                                   margin:0 0 8px;">EZ Construction</h1>
                                        <p style="color:rgba(255,255,255,0.7);font-size:13px;
                                                  margin:0;letter-spacing:1px;">
                                            ENTERPRISE RESOURCE PLANNING
                                        </p>
                                    </td>
                                </tr>

                                <!-- Body -->
                                <tr>
                                    <td style="padding:40px;">
                                        <h2 style="color:#0d1f5c;font-size:22px;font-weight:700;
                                                   margin:0 0 16px;">
                                            Demo Request Received! ✅
                                        </h2>
                                        <p style="color:#6b7280;font-size:15px;line-height:1.6;margin:0 0 16px;">
                                            Hi <strong style="color:#0d1f5c;">%s</strong>,
                                        </p>
                                        <p style="color:#6b7280;font-size:15px;line-height:1.6;margin:0 0 24px;">
                                            Thank you for requesting a demo of EZ Construction ERP.
                                            We've received your information and our team will review it shortly.
                                        </p>
                                        <div style="background:#f0f6ff;border-left:4px solid #1a56e8;
                                                    border-radius:0 8px 8px 0;
                                                    padding:16px 20px;margin:0 0 24px;">
                                            <p style="color:#1a56e8;font-size:14px;font-weight:600;
                                                      margin:0 0 6px;">
                                                What happens next?
                                            </p>
                                            <p style="color:#6b7280;font-size:13px;line-height:1.5;margin:0;">
                                                Our team will verify your request and reach out within
                                                24 hours to schedule your personalized demo session.
                                            </p>
                                        </div>
                                        <p style="color:#6b7280;font-size:14px;line-height:1.6;margin:0;">
                                            Questions? Contact us at
                                            <a href="mailto:support@ezconstruction.com"
                                               style="color:#1a56e8;text-decoration:none;font-weight:600;">
                                                support@ezconstruction.com
                                            </a>
                                        </p>
                                    </td>
                                </tr>

                                <!-- Footer -->
                                <tr>
                                    <td style="background:#f8fafc;padding:24px 40px;
                                               border-top:1px solid #e5e7eb;">
                                        <p style="color:#9ca3af;font-size:12px;
                                                  text-align:center;margin:0;">
                                            © 2025 EZ Construction. All rights reserved.<br>
                                            <a href="#" style="color:#1a56e8;text-decoration:none;">
                                                Privacy Policy
                                            </a>
                                            &nbsp;•&nbsp;
                                            <a href="#" style="color:#1a56e8;text-decoration:none;">
                                                Terms of Service
                                            </a>
                                        </p>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """.formatted(name);
    }

    public String buildVerificationEmail(String name) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>
            <body style="margin:0;padding:0;background-color:#f4f6f9;font-family:'Segoe UI',Tahoma,Geneva,Verdana,sans-serif;">
                <table width="100%%" cellpadding="0" cellspacing="0"
                    style="background-color:#f4f6f9;padding:40px 20px;">
                    <tr>
                        <td align="center">
                            <table width="600" cellpadding="0" cellspacing="0"
                                style="background-color:#ffffff;border-radius:16px;overflow:hidden;
                                       box-shadow:0 4px 24px rgba(0,0,0,0.08);">

                                <!-- Header -->
                                <tr>
                                    <td style="background:linear-gradient(135deg,#059669 0%%,#10b981 100%%);
                                               padding:40px 40px 30px;text-align:center;">
                                        <div style="width:56px;height:56px;background:rgba(255,255,255,0.15);
                                                    border-radius:14px;display:inline-block;
                                                    margin-bottom:16px;line-height:56px;">
                                            <span style="font-size:28px;">✅</span>
                                        </div>
                                        <h1 style="color:#ffffff;font-size:24px;font-weight:700;
                                                   margin:0 0 8px;">Request Verified!</h1>
                                        <p style="color:rgba(255,255,255,0.7);font-size:13px;
                                                  margin:0;letter-spacing:1px;">
                                            EZ CONSTRUCTION ERP
                                        </p>
                                    </td>
                                </tr>

                                <!-- Body -->
                                <tr>
                                    <td style="padding:40px;">
                                        <h2 style="color:#0d1f5c;font-size:22px;font-weight:700;
                                                   margin:0 0 16px;">
                                            Great News, %s! 🎉
                                        </h2>
                                        <p style="color:#6b7280;font-size:15px;line-height:1.6;margin:0 0 24px;">
                                            Your demo request has been
                                            <strong style="color:#059669;">verified and approved</strong>
                                            by our team.
                                        </p>
                                        <div style="background:#ecfdf5;border-left:4px solid #10b981;
                                                    border-radius:0 8px 8px 0;
                                                    padding:16px 20px;margin:0 0 24px;">
                                            <p style="color:#059669;font-size:14px;font-weight:600;
                                                      margin:0 0 6px;">
                                                What's next?
                                            </p>
                                            <p style="color:#6b7280;font-size:13px;line-height:1.5;margin:0;">
                                                A member of our team will contact you shortly to finalize
                                                the date and time for your personalized demo session.
                                                Get ready to explore the full power of EZ Construction ERP!
                                            </p>
                                        </div>
                                        <table cellpadding="0" cellspacing="0" style="margin:0 0 24px;">
                                            <tr>
                                                <td style="background:linear-gradient(135deg,#0d1f5c,#1a56e8);
                                                           border-radius:8px;padding:14px 32px;">
                                                    <a href="https://ezconstruction.com"
                                                       style="color:#ffffff;text-decoration:none;
                                                              font-size:14px;font-weight:700;
                                                              letter-spacing:0.5px;">
                                                        VISIT OUR PLATFORM →
                                                    </a>
                                                </td>
                                            </tr>
                                        </table>
                                        <p style="color:#6b7280;font-size:14px;line-height:1.6;margin:0;">
                                            Questions? Reach us at
                                            <a href="mailto:support@ezconstruction.com"
                                               style="color:#1a56e8;text-decoration:none;font-weight:600;">
                                                support@ezconstruction.com
                                            </a>
                                        </p>
                                    </td>
                                </tr>

                                <!-- Footer -->
                                <tr>
                                    <td style="background:#f8fafc;padding:24px 40px;
                                               border-top:1px solid #e5e7eb;">
                                        <p style="color:#9ca3af;font-size:12px;
                                                  text-align:center;margin:0;">
                                            © 2025 EZ Construction. All rights reserved.<br>
                                            <a href="#" style="color:#1a56e8;text-decoration:none;">
                                                Privacy Policy
                                            </a>
                                            &nbsp;•&nbsp;
                                            <a href="#" style="color:#1a56e8;text-decoration:none;">
                                                Terms of Service
                                            </a>
                                        </p>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """.formatted(name);
    }
}