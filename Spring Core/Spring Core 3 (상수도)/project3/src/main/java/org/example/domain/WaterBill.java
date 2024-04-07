package org.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WaterBill {
    private int number;
    private String city;
    private String sector;
    private int step;
    private long sectionStart;
    private long sectionEnd;
    private long unitPrice;
    private String sectorPrice;
    private long billTotal;

    public WaterBill(int number, String city, String sector, int step, long sectionStart, long sectionEnd,
                     long unitPrice,
                     String sectorPrice) {
        this.number = number;
        this.city = city;
        this.sector = sector;
        this.step = step;
        this.sectionStart = sectionStart;
        this.sectionEnd = sectionEnd;
        this.unitPrice = unitPrice;
        this.sectorPrice = sectorPrice;
    }

    public WaterBill() {
    }

    @JsonProperty("순번")
    public int getNumber() {
        return number;
    }

    @JsonProperty("지자체명")
    public String getCity() {
        return city;
    }

    @JsonProperty("업종")
    public String getSector() {
        return sector;
    }

    @JsonProperty("단계")
    public int getStep() {
        return step;
    }

    @JsonProperty("구간시작(세제곱미터)")
    public long getSectionStart() {
        return sectionStart;
    }

    @JsonProperty("구간끝(세제곱미터)")
    public long getSectionEnd() {
        return sectionEnd;
    }

    @JsonProperty("구간금액(원)")
    public long getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("단계별 기본요금(원)")
    public String getSectorPrice() {
        return sectorPrice;
    }

    public void setBillTotal(long billTotal) {
        this.billTotal = billTotal;
    }

    public long getBillTotal() {
        return billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal +
                '}';
    }
}
