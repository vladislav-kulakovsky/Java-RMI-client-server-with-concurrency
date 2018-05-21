package com.vk.dell.emc.client_server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServer extends Remote {
    public int nextRandomNumber() throws RemoteException;
}
