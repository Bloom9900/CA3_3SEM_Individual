package utils;

public class Keys {

    public static String digitalOceanBearer;
    public static String postNordKey;
    public static String weatherKey;
    public static String movieKey;
    public static String faceItBearer;
    
    public static void createKeys() {
        boolean isDeployed = (System.getenv("DEPLOYED") != null);
        if (isDeployed) {
            setDigitalOceanBearer(System.getenv("digitalOceanBearer"));
            setFaceItBearer(System.getenv("faceItBearer"));
            setMovieKey(System.getenv("movieKey"));
            setPostNordKey(System.getenv("postNordKey"));
            setWeatherKey(System.getenv("weatherKey"));
        } else {
            setDigitalOceanBearer(KeysHidden.digitalOceanBearer);
            setFaceItBearer(KeysHidden.faceItBearer);
            setMovieKey(KeysHidden.movieKey);
            setPostNordKey(KeysHidden.postNordKey);
            setWeatherKey(KeysHidden.weatherKey);
        }
    }

    public static String getDigitalOceanBearer() {
        return digitalOceanBearer;
    }

    public static void setDigitalOceanBearer(String digitalOceanBearer) {
        Keys.digitalOceanBearer = digitalOceanBearer;
    }

    public static String getPostNordKey() {
        return postNordKey;
    }

    public static void setPostNordKey(String postNordKey) {
        Keys.postNordKey = postNordKey;
    }

    public static String getWeatherKey() {
        return weatherKey;
    }

    public static void setWeatherKey(String weatherKey) {
        Keys.weatherKey = weatherKey;
    }

    public static String getMovieKey() {
        return movieKey;
    }

    public static void setMovieKey(String movieKey) {
        Keys.movieKey = movieKey;
    }

    public static String getFaceItBearer() {
        return faceItBearer;
    }

    public static void setFaceItBearer(String faceItBearer) {
        Keys.faceItBearer = faceItBearer;
    }
    
    
    
    
}
