package com.vinay.nagisetty.SpringbootEmbarkx.dto;

import lombok.Data;

@Data
public class OfferLetterRequest {
    private String candidateName;
    private String designation;
    private String joiningDate;
    private String companyName;
    private double basicSalary;
    private double hra;
    private double otherAllowances;
}

