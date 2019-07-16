package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
InetAddress:IP地址，无共有构造方法，InetAddress.getByName(host)/getByAddress(Byte[] address)

 */
public class URLt {
    public static void main(String[] args) throws IOException {
        java.net.URL url=new java.net.URL("https://gitee.com/smartlab/CS-Notes/blob/master/notes/Java%20IO.md");
        InputStream in=url.openStream();
        InputStreamReader inputStreamReade=new InputStreamReader(in,"UTF-8");
        BufferedReader bufferedReader=new BufferedReader(inputStreamReade);
        String line;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }
    }


}
