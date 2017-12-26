package org.audit4j.microservice.transport.thrift;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.core.Transport;

public class ThriftTransportServer extends Transport {

    public static TAuditHandler handler;

    public static TAuditService.Processor processor;

    private TServer tServer;

    private int serverPort = 9092;

    private String serverHost = "localhost";

    private String secureKeyStore;

    private String secureKeyPassword;

    private boolean multiThreadded = false;

    boolean secureServer = false;

    public ThriftTransportServer() {}
    
    public ThriftTransportServer(String host, int port, boolean multiThreadded) {
        this.serverHost = host;
        this.serverPort = port;
        this.multiThreadded = multiThreadded;
    }

    public ThriftTransportServer(String host, int port, boolean multiThreadded, String keyStore, String keyPassword) {
        this.serverHost = host;
        this.serverPort = port;
        this.multiThreadded = multiThreadded;
        this.secureKeyStore = keyStore;
        this.secureKeyPassword = keyPassword;
        secureServer = true;
    }

    @Override
    public void init() throws InitializationException {
        try {
            handler = new TAuditHandler(getReciever());
            processor = new TAuditService.Processor(handler);

            
            Runnable server = new Runnable() {
                public void run() {
                    if (secureServer) {
                        secure(processor);
                    } else {
                        simple(processor);
                    }
                    
                }
            };

            new Thread(server).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @Override
    public void stop() {
        tServer.stop();
    }

    public void simple(TAuditService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(new InetSocketAddress(serverHost, serverPort));
            tServer = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Simple RPC server started.! port: " + serverPort );
            tServer.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void secure(TAuditService.Processor processor) {
        try {
            TSSLTransportParameters params = new TSSLTransportParameters();
            // The Keystore contains the private key
            params.setKeyStore(secureKeyStore, secureKeyPassword, null, null);

            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(serverPort, 0,
                    InetAddress.getByName(serverHost), params);

            // Use this for a multi threaded server
            if (multiThreadded) {
                tServer = new TThreadPoolServer(
                        new TThreadPoolServer.Args(serverTransport).processor(processor));
            } else {
                tServer = new TSimpleServer(new Args(serverTransport).processor(processor));
            }

            tServer.serve();
            System.out.println("Secure RPC server started.! port: " + serverPort );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public void setSecureKeyStore(String secureKeyStore) {
        this.secureKeyStore = secureKeyStore;
    }

    public void setSecureKeyPassword(String secureKeyPassword) {
        this.secureKeyPassword = secureKeyPassword;
    }

    public void setMultiThreadded(boolean multiThreadded) {
        this.multiThreadded = multiThreadded;
    }

    public void setSecureServer(boolean secureServer) {
        this.secureServer = secureServer;
    }
}
