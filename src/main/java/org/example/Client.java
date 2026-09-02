package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {

    private HttpClient client;
    private RequestBuilder requestBuilder;

    public Client(){
        client = HttpClient.newHttpClient();
        requestBuilder=new RequestBuilder();
    }

    public UserResponse getUsers() throws Exception{

        HttpRequest request= requestBuilder.get("https://reqres.in/api/users?page=2");

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

        HttpRequest postRequest = requestBuilder.post("https://reqres.in/api/users",json);
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
        HttpRequest putRequest = requestBuilder.put("https://reqres.in/api/users/2", jsonUpdate);
        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("PUT response:");
        //System.out.println(putResponse.body());
    }

    public void deleteUser() throws Exception{
        HttpRequest deleteRequest = requestBuilder.delete("https://reqres.in/api/users/2");
        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println("DELETE status:");
        //System.out.println(deleteResponse.statusCode());
    }

}
