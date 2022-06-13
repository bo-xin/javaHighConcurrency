package com.tb.chapter5.EchoTest;
/**
*@author Tb
*@date 2022/6/1 15:42
*@description 实现NIO的服务端
*/
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadNioServer {
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    //记录时间
    public static Map<Socket,Long> time_stat = new HashMap<>(10240);

    public void startServer() throws Exception{
        //获取一个Selector的对象实例
        selector = SelectorProvider.provider().openSelector();
        //获得表示服务端的SocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置SocketChannel设置为非阻塞模式
        ssc.configureBlocking(false);
        //端口绑定
        //InetSocketAddress isa = new InetSocketAddress("localhost",8000);
        InetSocketAddress isa = new InetSocketAddress(8000);
        ssc.socket().bind(isa);
        //将ServerSocketChannel绑定到selector上
        SelectionKey acceptkey = ssc.register(selector,SelectionKey.OP_ACCEPT);
        //等待-分发网络任务
        for(;;){
            //阻塞方法，返回准备好的SelectionKey的数量
            selector.select();
            //获取准备好的selectionKey
            Set readykey = selector.selectedKeys();
            Iterator i = readykey.iterator();
            long e = 0;
            while (i.hasNext()){
                //得到SelectionKey实例
                SelectionKey sk = (SelectionKey)i.next();
                //移除元素
                i.remove();
                //判断是否是接收状态，是就接收
                if(sk.isAcceptable()){
                    doAccept(sk);
                    //判断是否可读
                }else if (sk.isValid() && sk.isReadable()){
                    if(!time_stat.containsKey(((SocketChannel)sk.channel()).socket())){
                        time_stat.put((((SocketChannel) sk.channel()).socket()),System.currentTimeMillis());
                        doRead(sk);
                    }
                    //判断是否可写
                }else if(sk.isValid() && sk.isWritable()){
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend"+(e-b)+"ms");
                }
            }
        }
    }


    private void doAccept(SelectionKey sk){
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try{
            clientChannel = server.accept();
            //设置通道为非阻塞模式
            clientChannel.configureBlocking(false);
            //将channel注册到选择器上，告诉selector现在对OP_READ感兴趣
            SelectionKey clientKey = clientChannel.register(selector,SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);
            InetAddress clientInetAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accept connection from "+clientInetAddress.getHostAddress()+".");
        } catch (IOException e) {
            System.out.println("Failed to accept new client.");
            e.printStackTrace();
        }
    }

    class EchoClient{
        private LinkedList<ByteBuffer> outq;

        public EchoClient() {
            outq = new LinkedList<>();
        }

        public LinkedList<ByteBuffer> getOutputQueue(){
            return outq;
        }

        public void enqueue(ByteBuffer bb){
            outq.addFirst(bb);
        }
    }

    private void doRead(SelectionKey sk){
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;
        try{
            len = channel.read(bb);
            if(len < 0){
                disconnect(sk);
                return;
            }
        } catch (IOException e) {
            System.out.println("Failed to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg(sk,bb));
    }

    class HandleMsg implements Runnable{
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }

    private void doWrite(SelectionKey sk){
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient) sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
        ByteBuffer bb = outq.getLast();
        try{
            int len = channel.write(bb);
            if(len == -1){
                disconnect(sk);
                return;
            }
            if(bb.remaining() == 0){
                outq.removeLast();
            }
        } catch (IOException e) {
            System.out.println("Failed to write to client.");
            e.printStackTrace();
            disconnect(sk);
        }
        if(outq.size() == 0){
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    private void disconnect(SelectionKey sk) {
        //sk.channel()方法用于取出这个Key对应的channel
        SocketChannel channel = (SocketChannel) sk.channel();

        InetAddress clientAddress = channel.socket().getInetAddress();
        System.out.println(clientAddress.getHostAddress() + " disconnected.");

        try {
            channel.close();
        } catch (Exception e) {
            System.out.println("Failed to close client socket channel.");
            e.printStackTrace();
        }
    }

    // Main entry point.
    public static void main(String[] args) {
        MultiThreadNioServer echoServer = new MultiThreadNioServer();
        try {
            echoServer.startServer();
        } catch (Exception e) {
            System.out.println("Exception caught, program exiting...");
            e.printStackTrace();
        }
    }
}
