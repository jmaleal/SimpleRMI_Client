/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplermi_client;

import rmi_interface.ExemploInterface;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RMI:Comunicação entre aplicações que permite que o cliente faça invocação
 * remota de métodos a objetos presentes no servidor. Para isso é criada uma
 * interface (no exemplo ExemploInterface) que tem de ser comum em ambos os
 * lados e inclusivamente o package de cliente e servidor onde está essa
 * interface têm de ter o mesmo nome. O objeto retornado tem de implementar a
 * Interface Serializable!
 *
 * Links: https://pt.wikipedia.org/wiki/RMI;
 *
 *
 * No exemplo o cliente chama o registo (1099) e o serviço "/RMIService",
 * criando um objeto com nome stub e fazendo cast para a Interface conhecida por
 * ambos. Deste modo é possível verificar a comunicação (pela receção de uma
 * String).
 *
 * @author jorgeleal
 */
public class SimpleRMI_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        //alertação das políticas de segurança
        System.getProperties().put("java.security.policy", "./server.policy");

        //se descomentar este código, vai ser criado um Security manager novo, perdendo as configurações criadas na linha anterior.
        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        ExemploInterface stub = (ExemploInterface) LocateRegistry.getRegistry(1099).lookup("/RMIService");

        /*outra forma de fazer o que faz a linha anterior*/
 /*Registry reg = LocateRegistry.getRegistry(1099);
        ExemploInterface stub = (ExemploInterface) reg.lookup("/corridaRemotoInterface");*/
        String msgRecebida = stub.mensagem("Jorge");
        System.out.println(msgRecebida);
    }

}
