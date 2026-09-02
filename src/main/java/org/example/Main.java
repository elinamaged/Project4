package org.example;

import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {

        Client client = new Client();

        //get
        CompletableFuture<UserResponse> future = client.getUsers();

        future.thenAccept(result -> {

            System.out.println("Page: " + result.getPage());
            System.out.println("Total users: " + result.getTotal());

            for (User user : result.getData()) {
                System.out.println(user.getFirst_name());
            }
        });

        //post
        CompletableFuture<?> postFuture = client.createUser();

        postFuture.thenAccept(response -> {System.out.println("\nPOST response:");
            System.out.println(response);
        });

        //Put
        CompletableFuture<?> putFuture = client.updateUser();

        putFuture.thenAccept(response -> {
            System.out.println("\nPUT response:");
            System.out.println(response);
        });

        //delete
        CompletableFuture<?> deleteFuture = client.deleteUser();

        deleteFuture.thenAccept(response -> {
            System.out.println("\nDELETE response:");
            System.out.println(response);
        });

    }
}
