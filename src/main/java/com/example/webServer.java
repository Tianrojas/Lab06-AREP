package com.example;

import spark.Spark;

import static spark.Spark.*;

public class webServer {
    public static void main(String[] args) {
        Spark.staticFiles.location("/public");
        port(getPort());
        //get("log", (req,res)->"{\"msg\":\"Primer mensaje, 24-02-2024 16:45:45\"}");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}