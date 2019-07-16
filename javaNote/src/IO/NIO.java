package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
基于块的数据打包和传输方式，速度快
channel(通道)：双向，可同时读写，FileChannel,SocketChannel,ServerSocketChannel
buffer(缓冲)：读写必须经过缓冲，实质数组，数据的结构性访问，ByteBuffer,CharBuffer,IntBuffer等
buffer属性：capacity（最大容量）,position(已经读写的字节数)，limit（还可以读写的字节数）
filp()进行读写的转换，转换后position为0，limit为实际包含字节数（或者最大字节数）后一位
一个数据块结束后clear(),全部复位
 */
public class NIO {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("D:\\Google download\\证件照.jpg");
        FileOutputStream fos=new FileOutputStream("C:\\Users\\22610\\Desktop\\fuzhi.jpg");
        //源文件通道
        FileChannel fc=fis.getChannel();
        //复制文件通道
        FileChannel foc=fos.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        //从一个通道里读取数据，需要先将数据读取到缓冲区
        while(fc.read(byteBuffer)!=-1){
            //转换读写，position为0，limit为读取字节数后一位
            byteBuffer.flip();
            //将缓冲区的数据写向一个通道
            foc.write(byteBuffer);
            //关闭
            byteBuffer.clear();
        }

    }
}
