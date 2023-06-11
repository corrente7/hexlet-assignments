package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    TcpConnection tcp;

    public Connected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");

    }

    @Override
    public void disconnect() {
        tcp.setConnection(new Disconnected(tcp));
        System.out.println("disconnected");

    }

    @Override
    public void write(String data) {
        System.out.println(data + " was added.");
    }
}


// END
