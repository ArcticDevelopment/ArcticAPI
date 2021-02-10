package dev.kyro.arcticapi;

public class ArcticAPI {

    public static String prefix;
    public static String errorPrefix;

    public ArcticAPI(String prefix, String errorPrefix) {

        ArcticAPI.prefix = prefix;
        ArcticAPI.errorPrefix = errorPrefix;
    }

    public static String getPrefix() {

        return prefix == null ? "" : prefix;
    }

    public static String getErrorPrefix() {

        return errorPrefix == null ? "" : errorPrefix;
    }
}
