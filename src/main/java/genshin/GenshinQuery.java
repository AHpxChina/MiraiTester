package genshin;

import data.genshin.GenshinIndex;
import utils.http.HttpUtilities;
import utils.json.JsonUtilities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Random;

public class GenshinQuery {
    public static void main(String[] args) throws Exception {

    }

    public static final String UserAgent = "Mozilla/5.0 (Linux; Android 11; Redmi Note 8 Build/RQ2A.210405.005; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.91 Mobile Safari/537.36 miHoYoBBS/2.9.0";

    //来自 @Azure99
    //https://github.com/Azure99/GenshinPlayerQuery/blob/main/src/Core/GenshinAPI.cs
    public static final String Salt = "w5k9n3aqhoaovgw25l373ee18nsazydo";

    public static final String AppVersion = "2.9.0";

    public static GenshinIndex getGenshinIndex(String uid, String cookie){
        var gson = new Gson();
        var json = getIndexJson(uid, cookie);

        var obj = gson.fromJson(json, JsonObject.class);

        try {
            return gson.fromJson(JsonUtilities.fetch(obj, "data"), GenshinIndex.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getIndexJson(String uid, String cookie){
        var headers = new HashMap<String, String>(){
            {put("User-Agent",UserAgent);}
            {put("DS",calculateDynamicSecret());}
            {put("x-rpc-client_type","5");}
            {put("x-rpc-app_version",AppVersion);}
            {put("Cookie", cookie);}
        };

        var url = "https://api-takumi.mihoyo.com/game_record/genshin/api/index?server=cn_gf01&role_id=" + uid;
        var response = HttpUtilities.get(url, headers);

        return response;
    }

    private static String calculateDynamicSecret(){
        var version = getMd5(AppVersion);
        var time = Instant.now().getEpochSecond();
        var rs = getRandomString(6);

        var suffix = getMd5("salt=" + Salt + "&t=" + time + "&r=" + rs);

        return time + "," + rs + "," + suffix;
    }

    private static String getMd5(String plaintext) {
        try {
            var formatter = new Formatter();
            var digest = MessageDigest.getInstance("MD5");
            digest.update(plaintext.getBytes(StandardCharsets.UTF_8));

            for (var b : digest.digest())
                formatter.format("%02x", b);
            return formatter.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static String getRandomString(int length){
        var table = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        var re = new StringBuilder();

        for (int i = 0; i < length; i++) {
            var random = new Random();

            re.append(table[random.nextInt(table.length)]);
        }

        return re.toString();
    }
}
