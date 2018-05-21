package com.vk.dell.emc.client_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class RmiServerImpl extends UnicastRemoteObject implements RmiServer {
    private Random random = new Random();

    protected RmiServerImpl() throws RemoteException {
        super();
    }

    @Override
    public int nextRandomNumber() throws RemoteException {
        return random.nextInt();
    }
}
