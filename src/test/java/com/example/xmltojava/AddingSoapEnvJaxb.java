package com.example.xmltojava;

import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AddingSoapEnvJaxb {
    @Test
    public void soapEnvelopeTest() throws ParserConfigurationException, SOAPException, IOException, JAXBException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Marshaller marshaller = JAXBContext.newInstance(SimpleBean.class).createMarshaller(); // SimpleBean must be JAXB generated class
        SimpleBean mybean = new SimpleBean(); // Does not work because it must be a request or response class generated by JAXB
        marshaller.marshal(mybean, document);
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);
        String output = new String(outputStream.toByteArray());
        assertEquals(output, "hi");
    }


}
