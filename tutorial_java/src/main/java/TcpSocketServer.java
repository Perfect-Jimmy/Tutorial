import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpSocketServer {
    /**
     * 监听的端口
     */
    public static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("服务器启动》》》》》》");
        TcpSocketServer server = new TcpSocketServer();
        server.init();
    }

    public void init(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true){
                //一旦有堵塞，表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();

                new HandlerThread(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class HandlerThread implements Runnable{
        private Socket socket;

        public HandlerThread(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }
        @Override
        public void run() {
            try {
                //客户端信息
                SocketAddress clientAddress = socket.getRemoteSocketAddress();
                System.out.println("Handling client at " + clientAddress);

                while(true){
                    //读取客户端数据
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    //这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
                    String clientInputStr = input.readUTF();
                    //处理客户端发送的数据
                    System.out.println("客户端发过来的内容："+clientInputStr);
                    //向客户端发送消息
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    System.out.println("请输入：\t");
                    String outStr = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    out.writeUTF(outStr);
                    out.close();
                    input.close();
                }

            } catch (IOException e) {
                System.out.println("服务器异常："+e.getMessage());
            }finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }


}
