import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;



public class FactorialServer {
    public static long calculateFactorial(int number) {
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    static class FactorialHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] queryParams = query.split("=");

            long result = 0;
            String responseMessage = "";

            if (queryParams.length == 2 && queryParams[0].equals("number")) {
                try {
                    int number = Integer.parseInt(queryParams[1]);
                    result = calculateFactorial(number);
                    responseMessage = "The factorial of " + number + " is: " + result;
                } catch (NumberFormatException e) {
                    responseMessage = "Invalid number format. Please provide a valid integer.";
                }
            } else {
                responseMessage = "Please provide a 'number' parameter in the URL. Example: /factorial?number=5";
            }
            exchange.sendResponseHeaders(200, responseMessage.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseMessage.getBytes());
            os.close();
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/factorial", new FactorialHandler());
        System.out.println("Server started at http://localhost:8080/factorial?number=<your_number>");

        server.start();
    }
}
