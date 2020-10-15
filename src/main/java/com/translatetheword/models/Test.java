package com.translatetheword.translatetheword.models;

public class Test {
    private String engword, rusword, translate;
    private boolean result;


    public Test() {
    }

    public Test(String engword, String rusword, String translate) {
        this.engword = engword;
        this.rusword = rusword;
        this.translate = translate;
    }


    public Test(boolean result) {
        this.result = result;
    }

    public String getEngword() {
        return engword;
    }

    public void setEngword(String engword) {
        this.engword = engword;
    }

    public String getRusword() {
        return rusword;
    }

    public void setRusword(String rusword) {
        this.rusword = rusword;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
