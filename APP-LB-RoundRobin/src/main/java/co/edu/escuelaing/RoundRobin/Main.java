package co.edu.escuelaing.RoundRobin;

import spark.Request;
import spark.Response;

import java.io.IOException;

import static spark.Spark.*;

public class Main {


    //docker build --tag tianrojas/logroundrobin .
    public static void main(String... args){
        staticFiles.location("/public");
        port(getPort());
        get("/logs/:page", (req, res) -> listLogs(req, res));
        post("/logs", (req, res) -> addLog(req, res));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String listLogs(Request req, Response res) throws IOException {
        int page = Integer.parseInt(req.params(":page"));
        String logs = String.valueOf(RRInvoker.listLogs(page));
        return logs;
    }

    private static String addLog(Request req, Response res) throws IOException {
        String message = req.body();
        RRInvoker.addLog(message);
        return "Log agregado exitosamente";
    }
}