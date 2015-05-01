package ca.johnson.game.net.packets;

import ca.johnson.game.net.GameClient;
import ca.johnson.game.net.GameServer;

public class Packet03Attack extends Packet {

    private String username;
    private int x, y;

    private int numSteps = 0;
    private boolean isMoving;
    private int movingDir = 1;

    public Packet03Attack(byte[] data) {
        super(03);
        String[] dataArray = readData(data).split(",");
        this.username = dataArray[0];
        this.x = Integer.parseInt(dataArray[1]);
        this.y = Integer.parseInt(dataArray[2]);
        this.movingDir = Integer.parseInt(dataArray[5]);
    }

    public Packet03Attack(String username, int x, int y, int numSteps, boolean isMoving, int movingDir) {
        super(02);
        this.username = username;
        this.x = x;
        this.y = y;
        this.numSteps = numSteps;
        this.isMoving = isMoving;
        this.movingDir = movingDir;
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("02" + this.username + "," + this.x + "," + this.y + "," + this.numSteps + "," + (isMoving ? 1 : 0)
                + "," + this.movingDir).getBytes();

    }

    public String getUsername() {
        return username;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getMovingDir() {
        return movingDir;
    }
}
