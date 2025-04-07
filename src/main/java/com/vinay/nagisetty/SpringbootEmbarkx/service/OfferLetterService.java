package com.vinay.nagisetty.SpringbootEmbarkx.service;


//import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vinay.nagisetty.SpringbootEmbarkx.dto.OfferLetterRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class OfferLetterService {

    public byte[] generateOfferLetterPDF(OfferLetterRequest request) {
        try {
            // Load external CSS
            InputStream cssStream = new ClassPathResource("templates/style.css").getInputStream();
            String cssContent = new String(cssStream.readAllBytes(), StandardCharsets.UTF_8);

            // HTML Template with dynamic data
            String htmlContent = "<!DOCTYPE html><html><head>"
                    + "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'/>"+
            "<style>" + cssContent + "</style></head><body>"
                    + "<div class='header'>Offer Letter</div>"
                    + "<div class='content'>"
                    + "<p>Dear <strong>" + request.getCandidateName() + "</strong>,</p>"
                    + "<p class='text-danger'>We are pleased to offer you the position of <strong>" + request.getDesignation() + "</strong> at <strong>" + request.getCompanyName() + "</strong>, effective from <strong>" + request.getJoiningDate() + "</strong>.</p>"
                    + "<h3>Salary Breakdown</h3>"
                    + "<table class='salary-table'>"
                    + "<tr><th>Component</th><th>Amount (INR)</th></tr>"
                    + "<tr><td>Basic Salary</td><td>" + request.getBasicSalary() + "</td></tr>"
                    + "<tr><td>HRA</td><td>" + request.getHra() + "</td></tr>"
                    + "<tr><td>Other Allowances</td><td>" + request.getOtherAllowances() + "</td></tr>"
                    + "<tr><td><strong>Total</strong></td><td><strong>" + (request.getBasicSalary() + request.getHra() + request.getOtherAllowances()) + "</strong></td></tr>"
                    + "</table></div>"
                    + "<div class='footer'>Best Regards, <br /> HR Team, " + request.getCompanyName() + "</div>"
                    + "</body></html>";

            // Generate PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}

