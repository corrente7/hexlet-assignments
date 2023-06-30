package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        String search = request.getParameter("search");
        int count = 0;
        for (String company: getCompanies()) {
            if (search == null || search.equals("")) {
                out.write(company + "\n");
                count = 1;
            } else if (company.contains(search)) {
                    out.write(company + "\n");
                    count = 2;
            }
        }
        if (count == 0) {
            out.println("Companies not found");
        }
        out.close();
        // END
    }
}
