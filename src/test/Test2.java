package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Test2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",8000));
    }

}
