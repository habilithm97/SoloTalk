package com.example.solotalk;

public class Talk {
    String str;

    public Talk(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Talk{" +
                "str='" + str + '\'' +
                '}';
    }
}
