package com.test.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.IntBuffer;

/**
 * Created by Jimmy. 2018/2/26  16:25
 */
public class BufferStart {
    private static final Logger LOGGER = LoggerFactory.getLogger(BufferStart.class);
    public static void main(String[] args) {
        //获取IntBuffer,设置capacity为10
        IntBuffer buffer = IntBuffer.allocate(10);
        LOGGER.info("position:{},limit:{},capacity:{}",buffer.position(),buffer.limit(),buffer.capacity());
        //put写入数据
        buffer.put(1);
        buffer.put(2);
    /*    buffer.put(3);
        buffer.put(4);
        buffer.put(5);
        buffer.put(6);
        buffer.put(7);
        buffer.put(8);
        buffer.put(9);
        buffer.put(10);*/
     // buffer.put(11);


        //
        LOGGER.info("position:{},limit:{},capacity:{}",buffer.position(),buffer.limit(),buffer.capacity());
        //flip写模式切换到读模式, position = 0 ,limit = 之前position的值
        buffer.flip();
        LOGGER.info("position:{},limit:{},capacity:{}",buffer.position(),buffer.limit(),buffer.capacity());
    }
}

/*
position:0,limit:10,capacity:10
position:2,limit:10,capacity:10
position:0,limit:2,capacity:10
*/