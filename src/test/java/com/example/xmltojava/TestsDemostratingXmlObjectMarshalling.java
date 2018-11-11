package com.example.xmltojava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsDemostratingXmlObjectMarshalling {


	@Test
	public void contextLoads() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new SimpleBean());
        assertEquals("<SimpleBean><x>1</x><y>2</y></SimpleBean>", xml);
    }

    @Test
    public void whenJavaSerializedToXmlFile_thenCorrect() throws IOException, IOException {
        // Arrange
	    XmlMapper xmlMapper = new XmlMapper();
        // Act
        xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
        // Assert
        File createdFile = new File("simple_bean.xml");
        assertNotNull(createdFile);
    }

    @Test
    public void whenJavaGotFromXmlStr_thenCorrect() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        SimpleBean value =
                xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>",
                        SimpleBean.class);
        assertTrue(value.getX() == 1 && value.getY() == 2);
    }
}
