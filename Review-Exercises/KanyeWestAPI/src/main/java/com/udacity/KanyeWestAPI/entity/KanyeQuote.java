package com.udacity.KanyeWestAPI.entity;

public class KanyeQuote {

    private String quote;

    public KanyeQuote() {
    }

    public KanyeQuote(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "KanyeQuote { " +
                "quote = '" + quote + '\'' +
                "}";
    }

}
