package com.vinay.nagisetty.SpringbootEmbarkx.controller;

import com.vinay.nagisetty.SpringbootEmbarkx.dto.OfferLetterRequest;
import com.vinay.nagisetty.SpringbootEmbarkx.service.OfferLetterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offer-letter")
public class OfferLetterController {

    private final OfferLetterService offerLetterService;

    public OfferLetterController(OfferLetterService offerLetterService) {
        this.offerLetterService = offerLetterService;
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> generateOfferLetter(@RequestBody OfferLetterRequest request) {
        byte[] pdfBytes = offerLetterService.generateOfferLetterPDF(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Offer_Letter.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
