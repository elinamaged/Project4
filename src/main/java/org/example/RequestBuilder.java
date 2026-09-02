package org.example;

import java.net.URI;
import java.net.http.HttpRequest;

public class RequestBuilder {
    private static final String APIkey="free_user_3IgO3hcjn2uHCjh371FEEmT1WlU";

    private HttpRequest.Builder builder(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-api-key", APIkey);
    }

    public HttpRequest get (String url){
        return builder(url)
                .GET()
                .build();
    }

    public HttpRequest post (String url, String json){
        return  builder(url)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    public HttpRequest put (String url, String json){
        return builder(url)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    public HttpRequest delete (String url){
        return builder(url)
                .DELETE()
                .build();
    }

}
