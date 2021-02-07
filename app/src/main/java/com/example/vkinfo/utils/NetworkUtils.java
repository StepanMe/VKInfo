package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String API_BASE_URL = "https://api.vk.com";
    private static final String API_METHOD = "/method/users.get";
    private static final String PARAM_USER_IDS = "user_ids";
    private static final String PARAM_FIELDS = "fields";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_ACCESS_KEY = "access_token";
    private static final String PARAM_LANG = "lang";

    /*
    Эта ссылка работать не будет, так как токен устарен
    https://api.vk.com/method/users.get?user_ids=1,2,3,4,5&fields=photo_200&access_token=f9b838c9f9b838c9f9b838c9f4f9ce22e5ff9b8f9b838c999b6f271a1e07182687a7184&v=5.126

    */
    public static URL generateURL(String userIds){
        Uri buildUri = Uri.parse(API_BASE_URL + API_METHOD)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_IDS,userIds)
                .appendQueryParameter(PARAM_FIELDS,"photo_200")
                .appendQueryParameter(PARAM_VERSION,"5.126")
                .appendQueryParameter(PARAM_ACCESS_KEY,"f9b838c9f9b838c9f9b838c9f4f9ce22e5ff9b8f9b838c999b6f271a1e07182687a7184")   //вставьте свой токен
                .appendQueryParameter(PARAM_LANG,"ru")
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasNext = scanner.hasNext();
            if (hasNext) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
