package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) throws Exception {

        Client client = new Client();

        UserResponse result = client.getUsers();

        System.out.println("Page: " + result.getPage());
        System.out.println("Total users: " + result.getTotal());

        for (User user: result.getData()){
            System.out.println(user.getFirst_name());
        }

        client.createUser();
        client.updateUser();
        client.deleteUser();

    }
}
