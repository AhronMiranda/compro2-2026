package com.practicesocket;

import com.practicesocket.models.Task;
import com.practicesocket.services.NetwordService;

public class Main {
    public static void main(String[] args) {
        NetwordService ns = new NetwordService();
        String host = "jsonplaceholder.typicode.com";
        int port = 443;
        String path = "/todos/1";
        String response = ns.fetchData(host, port, path);
        Gson gson = new Gson();
        Task task = gson.fromJson(response, Task.class);
        System.out.println(task);
    }
}