package com.vk.dell.emc.client_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) throws RemoteException {
        RmiServer server = new RmiServerImpl();

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("simple", new RmiServerImpl());
        System.out.println("Server started...");
    }
}
