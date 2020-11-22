package com.example.bookmytrip.recyclerViewClasses;

import androidx.annotation.NonNull;

public class ModalClassTkt {
    String srcValue, destValue, tktNoValue, spinnerValue, radioValue, tktAmtValue, dateTimeValue, emailValue;

    public ModalClassTkt(String tktNoValue, String srcValue, String destValue, String spinnerValue, String radioValue, String tktAmtValue, String dateTimeValue, String emailValue) {
        this.srcValue = srcValue;
        this.destValue = destValue;
        this.tktNoValue = tktNoValue;
        this.spinnerValue = spinnerValue;
        this.radioValue = radioValue;
        this.tktAmtValue = tktAmtValue;
        this.dateTimeValue = dateTimeValue;
        this.emailValue = emailValue;
    }

    public ModalClassTkt(String srcValue, String destValue, String tktAmtValue, String dateTimeValue) {
        this.srcValue = srcValue;
        this.destValue = destValue;
        this.tktAmtValue = tktAmtValue;
        this.dateTimeValue = dateTimeValue;
    }

    public String getSrcValue() {
        return srcValue;
    }

    public void setSrcValue(String srcValue) {
        this.srcValue = srcValue;
    }

    public String getDestValue() {
        return destValue;
    }

    public void setDestValue(String destValue) {
        this.destValue = destValue;
    }

    public String getTktNoValue() {
        return tktNoValue;
    }

    public void setTktNoValue(String tktNoValue) {
        this.tktNoValue = tktNoValue;
    }

    public String getSpinnerValue() {
        return spinnerValue;
    }

    public void setSpinnerValue(String spinnerValue) {
        this.spinnerValue = spinnerValue;
    }

    public String getRadioValue() {
        return radioValue;
    }

    public void setRadioValue(String radioValue) {
        this.radioValue = radioValue;
    }

    public String getTktAmtValue() {
        return tktAmtValue;
    }

    public void setTktAmtValue(String tktAmtValue) {
        this.tktAmtValue = tktAmtValue;
    }

    public String getDateTimeValue() {
        return dateTimeValue;
    }

    public void setDateTimeValue(String dateTimeValue) {
        this.dateTimeValue = dateTimeValue;
    }

    public String getEmailValue() {
        return emailValue;
    }

    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

}
