package rihanna.appsmatic.com.rihanna;

import android.os.StrictMode;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Eng Ali on 10/3/2017.
 */
public class RequestPayment {
    public static String request(Double amount,String currency) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String link="https://test.oppwa.com/v1/checkouts";
        URL url = new URL(link);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        String data = ""
                + "authentication.userId=8a8294174b7ecb28014b9699220015cc"
                + "&authentication.password=sy6KJsT8"
                + "&authentication.entityId=8a8294174b7ecb28014b9699220015ca"
                + "&amount=92.00"
                + "&currency=EUR"
                + "&paymentType=DB"
                + "&shopperResultUrl=my.app://custom/url"
                + "&notificationUrl=http://www.example.com/notify";

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();
        int responseCode = conn.getResponseCode();
        InputStream is;

        if (responseCode >= 400) is = conn.getErrorStream();
        else is = conn.getInputStream();


        return IOUtils.toString(is);
    }

}
