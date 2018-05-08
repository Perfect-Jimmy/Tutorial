import java.io.*;
import java.net.Socket;

public class TcpSocketClient {
    public static final int PORT = 12345;
    public static final String IP_ADDR = "localhost";

    public static void main(String[] args) {
        System.out.println("客户端启用》》》》》》");
        System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");
        while (true){
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR,PORT);
                socket.setSoTimeout(5000);
                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.print("请输入: \t");
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(str);

                String ret = input.readUTF();
                System.out.println("服务器端返回过来的是: " + ret);

                if (ret.endsWith("OK")) {
                    System.out.println("客户端将关闭连接");
                    Thread.sleep(500);
                    break;
                }
                out.close();
                input.close();


            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
