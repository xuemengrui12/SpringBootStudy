package com.xmr.config;

import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.Jetty;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Administrator
 * @Date: 9/4/2023
 * @LastEditTime: 9/4/2023 下午 10:09
 * @LastEditors: Administrator
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(HttpsProperties.class)
public class HttpsConfiguration {
    @Autowired
    private HttpsProperties properties;
    @Value("${server.port}")
    private int port;


    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {

        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {


            @Override

            public void customize(ConfigurableWebServerFactory factory) {

                ((JettyServletWebServerFactory) factory).addServerCustomizers(new JettyServerCustomizer() {

                    @Override
                    public void customize(Server server) {
                        //http
                        ServerConnector connector=new ServerConnector(server);
                        connector.setPort(port);
                        //https
                        SslContextFactory sslContextFactory=new SslContextFactory();
                        sslContextFactory.setKeyStorePath(properties.getKeyStoreFile());
                        sslContextFactory.setKeyStorePassword(properties.getKeyStorePsd());
                        HttpConfiguration configuration=new HttpConfiguration();
                        configuration.setSecureScheme(HttpScheme.HTTPS.asString());
                        configuration.addCustomizer(new SecureRequestCustomizer());

                        ServerConnector sslConnector=new ServerConnector(server,
                                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                                new HttpConnectionFactory(configuration));
                        sslConnector.setPort(properties.getPort());
                        server.setConnectors(new Connector[]{connector,sslConnector});
                    }

                });

            }


        };

    }
}
