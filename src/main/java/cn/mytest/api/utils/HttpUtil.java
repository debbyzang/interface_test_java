package cn.mytest.api.utils;

import cn.mytest.api.base.TestBase;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil extends TestBase {
    private static final String DEFAULT_CHARSET = "UTF-8";
    public static String userInfo;

    /**
     * get请求
     *
     * @param url
     * @param params
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public static String get(String url, Map<String, String> params) {
        try {
            if (url.startsWith ( "https" )) {
                return get ( initParams ( url, params ), true );
            } else {
                return get ( initParams ( url, params ), false );
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ( );
        } catch (NoSuchProviderException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        } catch (KeyManagementException e) {
            e.printStackTrace ( );
        }
        return null;
    }

    private static String get(String url, Boolean https) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        if (https) {
            return get4Https ( url );
        } else {
            System.out.println ( "getUrl: " + url );
            StringBuffer bufferRes = null;
            URL uri = new URL ( url );
            userInfo = uri.getUserInfo ( );
            if (userInfo != null && userInfo.length ( ) > 0) {
                userInfo = Base64.getEncoder ( ).encodeToString ( userInfo.getBytes ( ) );
                System.out.println ( "userInfo = " + userInfo );
            }
            HttpURLConnection http = (HttpURLConnection) uri.openConnection ( );
            // 连接超时
            http.setConnectTimeout ( 25000 );
            // 读取超时 --服务器响应比较慢，增大时间
            http.setReadTimeout ( 25000 );
            http.setRequestMethod ( "GET" );
            http.setRequestProperty ( "Content-Type", "application/x-www-form-urlencoded" );
            http.setDoOutput ( true );
            http.setDoInput ( true );
            http.connect ( );

            InputStream in = http.getInputStream ( );
            BufferedReader read = new BufferedReader ( new InputStreamReader ( in, DEFAULT_CHARSET ) );
            String valueString = null;
            bufferRes = new StringBuffer ( );
            while ((valueString = read.readLine ( )) != null) {
                bufferRes.append ( valueString );
            }
            in.close ( );
            if (http != null) {
                // 关闭连接
                http.disconnect ( );
            }
            System.out.println ( "getResult: " + bufferRes.toString ( ) );
            return bufferRes.toString ( );
        }
    }

    private static String initParams(String url, Map<String, String> params) {
        if (null == params || params.isEmpty ( )) {
            return url;
        }
        StringBuilder sb = new StringBuilder ( url );
        if (url.indexOf ( "?" ) == -1) {
            sb.append ( "?" );
        } else {
            sb.append ( "&" );
        }
        boolean first = true;
        for (Entry<String, String> entry : params.entrySet ( )) {
            if (first) {
                first = false;
            } else {
                sb.append ( "&" );
            }
            String key = entry.getKey ( );
            String value = entry.getValue ( );
            sb.append ( key ).append ( "=" );
            if (value != null && !"".equals ( value )) {
                try {
                    sb.append ( URLEncoder.encode ( value, DEFAULT_CHARSET ) );
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace ( );
                }
            } else {
                sb.append ( value );
            }
        }
        return sb.toString ( );
    }


    /**
     * 发送Get请求
     *
     * @param url
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws KeyManagementException
     */
    public static String get4Https(String url) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = {new MyX509TrustManager ( )};
        SSLContext sslContext = SSLContext.getInstance ( "SSL", "SunJSSE" );
        sslContext.init ( null, tm, new java.security.SecureRandom ( ) );
        SSLSocketFactory ssf = sslContext.getSocketFactory ( );
        URL uri = new URL ( url );
        HttpsURLConnection http = (HttpsURLConnection) uri.openConnection ( );
        http.setHostnameVerifier ( new CustomizedHostnameVerifier ( ) );
        // 连接超时
        http.setConnectTimeout ( 25000 );
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout ( 25000 );
        http.setRequestProperty ( "Authorization", "Bearer " + token );
        http.setRequestMethod ( "GET" );
        http.setRequestProperty ( "Content-Type", "application/x-www-form-urlencoded" );
        http.setSSLSocketFactory ( ssf );
        http.setDoOutput ( true );
        http.setDoInput ( true );
        http.connect ( );

        InputStream in = http.getInputStream ( );
        BufferedReader read = new BufferedReader ( new InputStreamReader ( in, DEFAULT_CHARSET ) );
        String valueString = null;
        bufferRes = new StringBuffer ( );
        while ((valueString = read.readLine ( )) != null) {
            bufferRes.append ( valueString );
        }
        in.close ( );
        if (http != null) {
            // 关闭连接
            http.disconnect ( );
        }
        return bufferRes.toString ( );
    }

    public static int delete(String url) {
        try {
            return get4Https ( url, "DELETE", "application/json" );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ( );
        } catch (NoSuchProviderException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        } catch (KeyManagementException e) {
            e.printStackTrace ( );
        }
        return -1000;
    }

    public static int get4Https(String url, String method, String contentType) throws NoSuchAlgorithmException,
            NoSuchProviderException,
            IOException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = {new MyX509TrustManager ( )};
        SSLContext sslContext = SSLContext.getInstance ( "SSL", "SunJSSE" );
        sslContext.init ( null, tm, new java.security.SecureRandom ( ) );
        SSLSocketFactory ssf = sslContext.getSocketFactory ( );
        URL uri = new URL ( url );
        HttpsURLConnection http = (HttpsURLConnection) uri.openConnection ( );
        http.setHostnameVerifier ( new CustomizedHostnameVerifier ( ) );
        // 连接超时
        http.setConnectTimeout ( 25000 );
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout ( 25000 );
        http.setRequestProperty ( "Authorization", "Bearer " + token );
        http.setRequestMethod ( method );
        http.setRequestProperty ( "Content-Type", contentType );
        http.setSSLSocketFactory ( ssf );
        http.setDoOutput ( true );
        http.setDoInput ( true );
        http.connect ( );

        InputStream in = http.getInputStream ( );
        BufferedReader read = new BufferedReader ( new InputStreamReader ( in, DEFAULT_CHARSET ) );
        String valueString = null;
        bufferRes = new StringBuffer ( );
        while ((valueString = read.readLine ( )) != null) {
            bufferRes.append ( valueString );
        }
        int responseCode = http.getResponseCode ( );
        System.out.println ( "responseCode = " + responseCode );
        in.close ( );
        if (http != null) {
            // 关闭连接
            http.disconnect ( );
        }
//        return bufferRes.toString ( );
        return responseCode;
    }

    public static String post4Htpps(String url, String params) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        return method4Htpps ( url, params, "POST", "application/json" );
    }

    public static String put(String url, String params) {
        try {
            return method4Htpps ( url, params, "PUT", "application/json" );
        } catch (IOException e) {
            e.printStackTrace ( );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ( );
        } catch (NoSuchProviderException e) {
            e.printStackTrace ( );
        } catch (KeyManagementException e) {
            e.printStackTrace ( );
        }
        return null;
    }

    public static String method4Htpps(String url, String params, String method, String contentType) throws
            IOException,
            NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = {new MyX509TrustManager ( )};
        SSLContext sslContext = SSLContext.getInstance ( "SSL", "SunJSSE" );
        sslContext.init ( null, tm, new java.security.SecureRandom ( ) );
        SSLSocketFactory ssf = sslContext.getSocketFactory ( );
        URL uri = new URL ( url );
        HttpsURLConnection http = (HttpsURLConnection) uri.openConnection ( );
        http.setHostnameVerifier ( new CustomizedHostnameVerifier ( ) );
        // 连接超时
        http.setConnectTimeout ( 25000 );
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout ( 25000 );
        http.setRequestProperty ( "Authorization", "Bearer " + token );
        http.setRequestMethod ( method );
        http.setRequestProperty ( "Content-Type", contentType );
        http.setSSLSocketFactory ( ssf );
        http.setDoOutput ( true );
        http.setDoInput ( true );
        http.connect ( );
        OutputStream out = http.getOutputStream ( );
        if (params != null) {
            out.write ( params.getBytes ( "UTF-8" ) );
        }
        out.flush ( );
        out.close ( );

        InputStream in = http.getInputStream ( );
        BufferedReader read = new BufferedReader ( new InputStreamReader ( in, DEFAULT_CHARSET ) );
        String valueString = null;
        bufferRes = new StringBuffer ( );
        while ((valueString = read.readLine ( )) != null) {
            bufferRes.append ( valueString );
        }
        in.close ( );
        if (http != null) {
            // 关闭连接
            http.disconnect ( );
        }
//        System.out.println ( "postResult = " + JsonUtil.formatJson ( bufferRes.toString ( ) ) );

        return bufferRes.toString ( );
    }

    public static String post(String url, String params) {
        System.out.println ( "请求url： " + url );
        try {
            if (url.startsWith ( "https" )) {
                return post4Htpps ( url, params );
            } else {
                return requestMethod ( url, params, "POST", "application/x-www-form-urlencoded" );
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ( );
        } catch (NoSuchProviderException e) {
            e.printStackTrace ( );
        } catch (KeyManagementException e) {
            e.printStackTrace ( );
        }
        return null;
    }

    public static String requestMethod(String url, String params, String method, String contentType) throws
            IOException,
            NoSuchAlgorithmException,
            NoSuchProviderException, KeyManagementException {
        StringBuffer bufferRes = null;
        URL urlGet = new URL ( url );
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection ( );
        // 连接超时
        http.setConnectTimeout ( 25000 );
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout ( 25000 );
        http.setRequestMethod ( method );
        http.setRequestProperty ( "Content-Type", contentType );
        http.setDoOutput ( true );
        http.setDoInput ( true );
        http.connect ( );

        OutputStream out = http.getOutputStream ( );
        out.write ( params.getBytes ( "UTF-8" ) );
        out.flush ( );
        out.close ( );

        InputStream in = http.getInputStream ( );
        BufferedReader read = new BufferedReader ( new InputStreamReader ( in, DEFAULT_CHARSET ) );
        String valueString = null;
        bufferRes = new StringBuffer ( );
        while ((valueString = read.readLine ( )) != null) {
            bufferRes.append ( valueString );
        }
        in.close ( );
        if (http != null) {
            // 关闭连接
            http.disconnect ( );
        }
        return bufferRes.toString ( );
    }

}

class CustomizedHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String arg0, SSLSession arg1) {
        return true;
    }

}

/**
 * 证书管理
 */
class MyX509TrustManager implements X509TrustManager {

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
}
