package com.example.vkinfo.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_API_METHOD = "/method/users.get";
    private static final String PARAM_USER_IDS = "user_ids";
    private static final String PARAM_FIELDS = "fields";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_ACCESS_KEY = "access_token";
    private static final String PARAM_LANG = "lang";

    //https://api.vk.com/method/users.get?user_ids=1,2,3,4,5&fields=photo_50&access_token=f9b838c9f9b838c9f9b838c9f4f9ce22e5ff9b8f9b838c999b6f271a1e07182687a7184&v=5.126
    public static URL generateURL(String userIds){
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_API_METHOD)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_IDS,userIds)
                .appendQueryParameter(PARAM_FIELDS,"photo_50")
                .appendQueryParameter(PARAM_VERSION,"5.126")
                .appendQueryParameter(PARAM_ACCESS_KEY,"f9b838c9f9b838c9f9b838c9f4f9ce22e5ff9b8f9b838c999b6f271a1e07182687a7184")
                .appendQueryParameter(PARAM_LANG,"ru")
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
            Log.i("A123A","NetworkUtils.java, строка 36 - OK");
        } catch (MalformedURLException e) {
            Log.i("A123A","NetworkUtils.java, строка 38 - Error");
            e.printStackTrace();
        }
        Log.i("A123A","NetworkUtils.java, строка 41 - OK");
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
                Log.i("A123A","NetworkUtils.java, строка 55 - OK");
                return scanner.next();
            } else {
                Log.i("A123A","NetworkUtils.java, строка 58 - Error");
                return null;
            }
        } catch (UnknownHostException e) {
            Log.i("A123A","NetworkUtils.java, строка 62 - Error");
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
