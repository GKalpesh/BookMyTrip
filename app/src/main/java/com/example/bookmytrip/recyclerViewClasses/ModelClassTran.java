package com.example.bookmytrip.recyclerViewClasses;

public class ModelClassTran {

    String srcTran, destTran, tktAmtTran, dateTimeTran;

    public ModelClassTran(String srcTran, String destTran, String tktAmtTran, String dateTimeTran) {
        this.srcTran = srcTran;
        this.destTran = destTran;
        this.tktAmtTran = tktAmtTran;
        this.dateTimeTran = dateTimeTran;
    }

    public String getSrcTran() {
        return srcTran;
    }

    public void setSrcTran(String srcTran) {
        this.srcTran = srcTran;
    }

    public String getDestTran() {
        return destTran;
    }

    public void setDestTran(String destTran) {
        this.destTran = destTran;
    }

    public String getTktAmtTran() {
        return tktAmtTran;
    }

    public void setTktAmtTran(String tktAmtTran) {
        this.tktAmtTran = tktAmtTran;
    }

    public String getDateTimeTran() {
        return dateTimeTran;
    }

    public void setDateTimeTran(String dateTimeTran) {
        this.dateTimeTran = dateTimeTran;
    }
}
