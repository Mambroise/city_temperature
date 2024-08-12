package city_temparature_servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Landing extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Remplace ta_clé_api par ta vraie clé API
    private static final String API_KEY = "c85d4770e6baed8cf5e48f28e1d861b5";

    public Landing() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Afficher la page d'accueil ou une vue par défaut
        request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("cityName");
        // Exemple de coordonnées fixes pour Paris
        String lat = "48.8566";
        String lon = "2.3522";

        // Crée l'URL pour l'API
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric", lat, lon, API_KEY);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Passer la réponse JSON à la JSP
                request.setAttribute("weatherData", content.toString());
                // Utiliser un forward pour la JSP
                request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
            } else {
                // Écrire le message d'erreur dans la requête
                request.setAttribute("errorMessage", "Failed to get weather data.");
                // Utiliser un forward pour la JSP avec message d'erreur
                request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Écrire le message d'erreur dans la requête
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            // Utiliser un forward pour la JSP avec message d'erreur
            request.getRequestDispatcher("/view/landing.jsp").forward(request, response);
        }
    }
}
