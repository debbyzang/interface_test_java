package cn.mytest.api.utils;

import cn.mytest.api.base.TestBase;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil extends TestBase {
    private static final String DEFAULT_CHARSET = "UTF-8";


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
    public static String get(String url, Map<String, String> params)
            throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        if (url.startsWith ( "https" )) {
            return get ( initParams ( url, params ), true );
        } else {
            return get ( initParams ( url, params ), false );
        }
    }

    private static String get(String url, Boolean https) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
        if (https) {
            return get4Https ( url );
        } else {
            System.out.println ( "getUrl: " + url );
            StringBuffer bufferRes = null;
            URL urlGet = new URL ( url );
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection ( );
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
        // 从上述SSLContext对象中得到SSLSocketFactory对象  
        SSLSocketFactory ssf = sslContext.getSocketFactory ( );

        URL urlGet = new URL ( url );
        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection ( );
        http.setHostnameVerifier ( new CustomizedHostnameVerifier ( ) );
        // 连接超时
        http.setConnectTimeout ( 25000 );
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout ( 25000 );
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

    public static String post4Htpps(String url, String params) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        StringBuffer bufferRes = null;
        TrustManager[] tm = {new MyX509TrustManager ( )};
        SSLContext sslContext = SSLContext.getInstance ( "SSL", "SunJSSE" );
        sslContext.init ( null, tm, new java.security.SecureRandom ( ) );
        // 从上述SSLContext对象中得到SSLSocketFactory对象  
        SSLSocketFactory ssf = sslContext.getSocketFactory ( );

        URL urlGet = new URL ( url );
        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection ( );
        http.setHostnameVerifier ( new CustomizedHostnameVerifier ( ) );
        // 连接超时
        http.setConnectTimeout ( 25000 );
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout ( 25000 );
        http.setRequestMethod ( "POST" );
        http.setRequestProperty ( "Content-Type", "application/x-www-form-urlencoded" );
        http.setSSLSocketFactory ( ssf );
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

    public static String post(String url, String params) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        System.out.println ( "请求url： " + url );
        if (url.startsWith ( "https" )) {
            return post4Htpps ( url, params );
        } else {
            return requestMethod ( url, params, "POST", "application/x-www-form-urlencoded" );
        }
    }

    public static String put(String url, String params) throws
            IOException,
            NoSuchAlgorithmException,
            NoSuchProviderException, KeyManagementException {
        return requestMethod ( url, params, "PUT", "application/x-www-form-urlencoded" );
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
