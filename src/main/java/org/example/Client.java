package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;

public class Client {

    private HttpClient client;
    private RequestBuilder requestBuilder;
    private ObjectMapper mapper;


    public Client(){
        client = HttpClient.newHttpClient();
        requestBuilder=new RequestBuilder();
        mapper = new ObjectMapper();
    }

    public CompletableFuture<UserResponse> getUsers() {

        HttpRequest request = requestBuilder.get("https://reqres.in/api/users?page=2");

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        return future.thenApply(response -> {
            try {
                return mapper.readValue(
                        response.body(),
                        UserResponse.class
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<HttpResponse<String>> createUser() throws Exception{
        String json = """
                {
                  "name": "Elina",
                  "job": "Developer"
                }
                """;

        HttpRequest postRequest = requestBuilder.post("https://reqres.in/api/users",json);

        return client.sendAsync(postRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("POST response:");
        //System.out.println(postResponse.body());
    }

    public CompletableFuture<HttpResponse<String>> updateUser() throws Exception{
        String jsonUpdate = """
                {
                  "name": "Elina",
                  "job": "Junior Developer"
                }
                """;
        HttpRequest putRequest = requestBuilder.put("https://reqres.in/api/users/2", jsonUpdate);
        return client.sendAsync(putRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("PUT response:");
        //System.out.println(putResponse.body());
    }

    public CompletableFuture<HttpResponse<String>> deleteUser() throws Exception{
        HttpRequest deleteRequest = requestBuilder.delete("https://reqres.in/api/users/2");
        return client.sendAsync(deleteRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("DELETE status:");
        //System.out.println(deleteResponse.statusCode());
    }

}
