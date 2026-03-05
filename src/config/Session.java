package config;

public class Session {

    
    
    private static String username;

    public static void setUsername(String user) {
        username = user;
    }

    public static String getUsername() {
        return username;
    }
    
    
    public static boolean isLoggedIn() {
        return username != null;
    }
    
    public static void logout() {
        username = null;
    }
    
    
}
