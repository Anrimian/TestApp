package com.testapp.utls.project;

/**
 * Created on 17.01.2017.
 */

public class Price {

    private int ruble;
    private int kopeck;

    public Price(int ruble, int kopeck) {
        this.kopeck = kopeck;
        this.ruble = ruble;
    }

    public Price(String price) {
        String[] pieces = price.split("\\.");
        ruble = Integer.parseInt(pieces[0]);
        kopeck = Integer.parseInt(pieces[1]);
    }

    public int getKopeck() {
        return kopeck;
    }

    public int getRuble() {
        return ruble;
    }

    @Override
    public String toString() {
        return "Price{" +
                "kopeck=" + kopeck +
                ", ruble=" + ruble +
                '}';
    }
}
