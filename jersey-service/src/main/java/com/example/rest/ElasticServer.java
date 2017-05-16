package com.example.rest;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * Created by Administrator on 08/05/2017.
 */
public class ElasticServer {

    public TransportClient client;


    public ElasticServer() {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-cluster").build();

        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300))

    }

    public void close() {

        client.close();
    }
}
