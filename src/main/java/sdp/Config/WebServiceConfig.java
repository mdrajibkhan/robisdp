package sdp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import sdp.Util.Error.DetailSoapFaultDefinitionExceptionResolver;
import sdp.Util.Error.ServiceFaultException;
import sdp.Util.Utility;

import java.util.Properties;

/**
 * Created by Hp on 10/29/2017.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Autowired
    Utility utility;

    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver() {
        SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();
        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties properties = new Properties();
        properties.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        properties.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(properties);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }


    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        utility.showMessage(servlet.getNamespace());
        return new ServletRegistrationBean(servlet, "/robisdp/*");
    }

   /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [XsdSchema]
    *  Workflow ->
    *  1. It takes XSD schema
    *  2. Set uri and namespace for wsdl file.
    *  2. Create the wsdl file.

    * Impact ->  It is used for converting  XSD file  for WSDL file .
    *
    * */


    @Bean(name = "syncRelation")
    public DefaultWsdl11Definition countryWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SyncRelationPort");
        wsdl11Definition.setLocationUri("/robisdp");
        wsdl11Definition.setTargetNamespace("http://www.csapi.org/schema/parlayx/data/sync/v1_0/local");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [XsdSchema]
    *  Workflow ->
    *  1.Define the XSD file for convert


    * Impact ->  It is used for define the  XSD file  for WSDL file .
    *
    * */


    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("dataSyncRelation.xsd"));
    }


    /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [XsdSchema]
    *  Workflow ->
    *  1. It takes XSD schema
    *  2. Set uri and namespace for wsdl file.
    *  2. Create the wsdl file.

    * Impact ->  It is used for converting  XSD file  for WSDL file .
    *
    * */

    @Bean(name = "test")
    public DefaultWsdl11Definition employeeWsdl11Definition(XsdSchema employeeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("robi_sdp");
        wsdl11Definition.setSchema(employeeSchema);
        return wsdl11Definition;
    }

    /*
    * FOLLOWING BEANS ARE GENERATED FOR
    * =================================
    *  Parameters -> [XsdSchema]
    *  Workflow ->
    *  1.Define the XSD file for convert
    *
    * Impact ->  It is used for define the  XSD file  for WSDL file .
    *
    * */



}
