public class JWTUtil
{
    /*
    private static final String key = "sayonara";
    public static String getToken(Map<String, Object> claims){
        return JWT.create()
                .withClaim("claims",claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .sign(Algorithm.HMAC256(key));
    }
    public static Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
    */
}
