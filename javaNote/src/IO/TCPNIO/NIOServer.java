package IO.TCPNIO;
/*
网络通信：服务端通过一个线程使用Selector轮询监听所有注册的通道,通道配置为非阻塞模式
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //选择器
        Selector selector=Selector.open();
        //服务器端通道
        ServerSocketChannel ssc=ServerSocketChannel.open();
        //通道配置为非阻塞
        ssc.configureBlocking(false);
        //通道注册到选择器上
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //serverSocker,绑定主机与端口
        ServerSocket serverSocket=new ServerSocket();
        InetSocketAddress address=new InetSocketAddress("127.0.0.1",8888);
        serverSocket.bind(address);
        while(true){
            //轮训监听
            selector.select();
            //迭代key
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator=selectionKeys.iterator();
            if(selectionKeyIterator.hasNext()){
                SelectionKey selectionKey=selectionKeyIterator.next();
                if(selectionKey.isAcceptable()){
                   ServerSocketChannel ssl1= (ServerSocketChannel) selectionKey.channel();
                    SocketChannel sc=ssl1.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                }
                else if(selectionKey.isReadable()){
                    SocketChannel sc= (SocketChannel) selectionKey.channel();
                    System.out.println(readDataFromSocketChannel(sc));
                    sc.close();
                }
                selectionKeyIterator.remove();
            }
        }

    }
    private static String readDataFromSocketChannel(SocketChannel sc) throws IOException {
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        StringBuilder sb=new StringBuilder();
        while(true){
            byteBuffer.clear();
            int num=sc.read(byteBuffer);
            if(num==-1){
                break;
            }
            byteBuffer.flip();
            int limit=byteBuffer.limit();
            char[] data=new char[limit];
            for(int i=0;i<limit;i++){
                data[i]= (char) byteBuffer.get(i);
            }
            sb.append(data);
            byteBuffer.clear();
        }
       return  sb.toString();
    }

}
