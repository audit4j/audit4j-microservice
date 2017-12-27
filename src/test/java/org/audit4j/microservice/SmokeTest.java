package org.audit4j.microservice;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.audit4j.microservice.transport.thrift.TAck;
import org.audit4j.microservice.transport.thrift.TAuditEvent;
import org.audit4j.microservice.transport.thrift.TAuditService;

public class SmokeTest {

    public static void main(String [] args) {
        try {
          TTransport transport;
         
          transport = new TSocket("localhost", 9092);
          transport.open();
          
         
         // Uncomment below lines for secure RPC communication
         // TSSLTransportFactory.TSSLTransportParameters params =
         //        new TSSLTransportFactory.TSSLTransportParameters();
         // params.setTrustStore("lib/truststore.jks", "123456");

         // transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 10000, params);


          TProtocol protocol = new  TBinaryProtocol(transport);
          TAuditService.Client client = new TAuditService.Client(protocol);

          perform(client);

          transport.close();
        } catch (TException x) {
          x.printStackTrace();
        } 
      }

      private static void perform(TAuditService.Client client) throws TException
      {
        TAuditEvent event = new TAuditEvent();
        event.setAction("aaa");
        event.setActor("Dumb");
        event.setTimestamp("12/07/1017");
        event.setTimestampFormat("dd/MM/yyyy");
        event.setUuid(12);
        
        TAck ack = client.audit(event);
        System.out.println("Ack=====" + ack.getCode());
      }
}
