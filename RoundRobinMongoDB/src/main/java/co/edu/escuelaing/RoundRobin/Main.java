package co.edu.escuelaing.RoundRobin;

import com.mongodb.client.MongoDatabase;

import org.json.JSONArray;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

//docker build --tag tianrojas/roundrobinbd .
//docker-compose up -d
//curl -X POST -d "Este es un mensaje de ejemplo" http://localhost:34000/logs

public class Main {

    static MongoDatabase database;
    static ServiceDAO serviceDAO;

    public static void main(String[] args) {
        database = MongoUtil.getDB();
        serviceDAO = new ServiceDAO(database);
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

    private static String listLogs(Request req, Response res) {
        int page = Integer.parseInt(req.params(":page"));
        String logs = String.valueOf(serviceDAO.listLogs(page));
        return logs;
    }

    private static String addLog(Request req, Response res) {
        String message = req.body();
        serviceDAO.addLog(message);
        return "Log agregado exitosamente";
    }
}