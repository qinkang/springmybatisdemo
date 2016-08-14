package com.qingofun.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * Created by qinkang on 16/6/28.
 */
public class MyCustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
    private final HostnameVerifier hostNameVerifier;


    public MyCustomClientHttpRequestFactory(final HostnameVerifier hostNameVerifier) {
        this.hostNameVerifier = hostNameVerifier;
    }

    public MyCustomClientHttpRequestFactory() {
        this.hostNameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
    }

    //10.100.140.85:1080
    public MyCustomClientHttpRequestFactory(String proxyHost, int proxyPort) {
        this();
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort));
        super.setProxy(proxy);
    }

    public MyCustomClientHttpRequestFactory(Proxy proxy) {
        this();
        super.setProxy(proxy);
    }

    @Override
    protected void prepareConnection(final HttpURLConnection connection, final String httpMethod)
            throws IOException {
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setHostnameVerifier(hostNameVerifier);
            ((HttpsURLConnection) connection).setSSLSocketFactory(initSSLContext()
                    .getSocketFactory());
        }
        super.prepareConnection(connection, httpMethod);
    }

    private SSLContext initSSLContext() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance(SSLSocketFactory.SSL);
            sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslcontext;
    }

    //自定义私有类
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}

