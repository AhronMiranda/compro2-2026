package com.practicesocket;

import com.practicesocket.services.NetwordService;

public class Main {
    public static void main(String[] args) {
        NetwordService ns = new NetwordService();
        String host = "jsonplaceholder.typicode.com";
        int port = 443;
        String path = "/posts/1/comments";
        String response = ns.fetchData(host, port, path);
        System.out.println(response);
    }
}