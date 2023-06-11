package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{

    TcpConnection tcp;

    public Disconnected(TcpConnection tcp) {
        this.tcp = tcp;
    }


    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        tcp.setConnection(new Connected(tcp));
        System.out.println("connected");

    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");

    }

    @Override
    public void write(String data) {
        System.out.println("Error! Connection disconnected");
    }
}
// END
