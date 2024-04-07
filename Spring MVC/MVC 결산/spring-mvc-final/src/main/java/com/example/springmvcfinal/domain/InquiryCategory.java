package com.example.springmvcfinal.domain;

import com.example.springmvcfinal.exception.NoSuchInquiryCategoryException;
import lombok.Getter;

@Getter
public enum InquiryCategory {
    COMPLAINT_SUBMISSION("불만 접수"),
    PROPOSAL("제안"),
    REFUND_OR_EXCHANGE("환불/교환"),
    COMPLIMENT("칭찬해요"),
    OTHER_INQUIRIES("기타 문의");

    private final String value;

    InquiryCategory(String value) {
        this.value = value;
    }

    public static InquiryCategory fromString(String value) {
        for (InquiryCategory category : values()) {
            if (category.getValue().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new NoSuchInquiryCategoryException("No such category: " + value);
    }


}
