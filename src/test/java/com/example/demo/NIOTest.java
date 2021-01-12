package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

@SpringBootTest
public class NIOTest {

    @Test
    public void fileChannel(){
        FileInputStream fileInputStream = null;
        try{
            Charset charset = Charset.forName("utf-8");//Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
            CharsetDecoder decoder = charset.newDecoder();

            //也可使用FileInputStream
            RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\test.txt","rw");
            FileChannel fileChannel = raf.getChannel();
            //定义缓冲区,分配1024字节空间
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            CharBuffer charBuffer = CharBuffer.allocate(512);
                    //读取缓冲区数据
            int readData = fileChannel.read(byteBuffer);
            while (readData != -1){
                //flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
                byteBuffer.flip();
                decoder.decode(byteBuffer, charBuffer, false);
                charBuffer.flip();

                //当buffer还剩余其他未读信息
                while(charBuffer.hasRemaining()){
                    System.out.print(charBuffer.get());
                }
//                clear()方法会清空整个缓冲区。
//                compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
                byteBuffer.clear();
                charBuffer.clear();
//                buffer.compact();
                readData = fileChannel.read(byteBuffer);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}
