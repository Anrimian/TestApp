package com.testapp;

import com.testapp.utls.network.Utils;
import com.testapp.utls.network.api.FarforApiWrapper;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class XmlParserTest {

    @Test
    public void testXmlParser() {
        FarforApiWrapper.getFarforApi()
                .getData(Utils.API_KEY)
                .subscribe(infoResponse -> {
                    System.out.println("response: " + infoResponse);
                }, throwable -> {
                    System.out.println("error");
                    throwable.printStackTrace();
                });
    }
}