package com.vk.dell.emc.client_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    private final static int port = 5099;

    public static void main(String[] args) throws RemoteException {

        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("simple", new RmiServerImpl());
        System.out.println("Server started...");

    }
}
