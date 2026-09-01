package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {

    private HttpClient client;

    public Client(){
        client = HttpClient.newHttpClient();
    }

    public UserResponse getUsers() throws Exception{

        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users?page=2"))
                .header("x-api-key", "free_user_3IgO3hcjn2uHCjh371FEEmT1WlU")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper= new ObjectMapper();
        UserResponse result = mapper.readValue(response.body(), UserResponse.class);

        return result;
    }

    public void createUser() throws Exception{
        String json = """
                {
                  "name": "Elina",
                  "job": "Developer"
                }
                """;

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users"))
                .header("x-api-key", "free_user_3IgO3hcjn2uHCjh371FEEmT1WlU")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("POST response:");
        //System.out.println(postResponse.body());
    }

    public void updateUser() throws Exception{
        String jsonUpdate = """
                {
                  "name": "Elina",
                  "job": "Junior Developer"
                }
                """;
        HttpRequest putRequest =HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users/2"))
                .header("x-api-key", "free_user_3IgO3hcjn2uHCjh371FEEmT1WlU")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUpdate))
                .build();
        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("PUT response:");
        //System.out.println(putResponse.body());
    }

    public void deleteUser() throws Exception{
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://reqres.in/api/users/2"))
                .header("x-api-key", "free_user_3IgO3hcjn2uHCjh371FEEmT1WlU")
                .DELETE()
                .build();
        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("DELETE status:");
        //System.out.println(deleteResponse.statusCode());
    }

}
