package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/users.json");
        List<User> usersList = objectMapper.readValue(file, new TypeReference<>() {
        });
        return usersList;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

            StringBuilder body = new StringBuilder();
            body.append("""
                    <!DOCTYPE html>
                    <html lang=\"ru\">
                        <head>
                            <meta charset=\"UTF-8\">
                            <title>Example application</title>
                            <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                                  rel=\"stylesheet\"
                                  integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                                  crossorigin=\"anonymous\">
                        </head>
                            <body>
                            <table>
                        """);
            for (User user : getUsers()) {
                body.append("<tr><td>" + user.getId() + "</td>");
                body.append("<td>" + "<a href=\"/users/" + user.getId() + "\">"
                        + user.getFirstName() + " " + user.getLastName() + "</a></td></tr>");
            }

            body.append("""
                        </table>
                        </body>
                    </html>
                    """);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(body);
        }

            // END

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder();
        response.setContentType("text/html;charset=UTF-8");
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                        <body>
                        <table>
                    """);
        int count = 0;
        for (User user : getUsers()) {
            if (id.equals(user.getId())) {
                body.append("<tr><td>" + user.getId() + "</td></tr>");
                body.append("<tr><td>" + user.getFirstName() + " " + user.getLastName() + "</td></tr>");
                body.append("<tr><td>" + user.getEmail() + "</td></tr>");
                count = 1;
                break;
            }
        }
        if (count == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
            body.append("""
                        </table>
                        </body>
                    </html>
                    """);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(body);
        }
    }
