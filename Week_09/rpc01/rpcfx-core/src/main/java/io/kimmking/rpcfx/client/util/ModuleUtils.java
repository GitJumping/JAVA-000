package io.kimmking.rpcfx.client.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleUtils {

    static Pattern pattern = Pattern.compile("http://([0-9.]+):([0-9]{2,5})(/.*)");

    public static String extractIp(String  url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        Matcher matcher = pattern.matcher(url);

        boolean b = matcher.find();
        if (b) {
            String ip = matcher.group(1);
            return ip;
        }
        return null;
    }

    public static String[] extractIpAndPort(String  url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        Matcher matcher = pattern.matcher(url);

        boolean b = matcher.find();
        if (b) {
            String ip = matcher.group(1);
            String port = matcher.group(2);
            return new String[]{ip,port};
        }

        return null;
    }

    public static String extractPort(String  url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        Matcher matcher = pattern.matcher(url);

        boolean b = matcher.find();
        if (b) {
            String port = matcher.group(2);

            return port;
        }
        return null;
    }
}
