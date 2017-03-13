package servlets;

import ldapConnection.CertificateConnector;
import ldapConnection.LdapConnector;
import ldapConnection.MasterConnector;
import org.forgerock.opendj.ldap.ErrorResultException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 23.02.2017.
 */
@WebServlet("/login")
public class Login extends HttpServlet{
    final String KEYSTORE_PATH = "/home/vlad/Licenta/workspace/licenta_opendj_user/src/main/resources/client-certs/c-keystore";
    final String STOREPASS = "qwerty";
    final String TRUSTSTORE_PATH = "/home/vlad/Licenta/opendj/config/truststore";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LdapConnector ldapConnector = new CertificateConnector(KEYSTORE_PATH, STOREPASS, TRUSTSTORE_PATH);

        Boolean isConnected = false;
        try {
            isConnected = ldapConnector.login(username, password);
            System.out.println(isConnected);
        } catch (ErrorResultException e) {
            e.printStackTrace();
        }

        try {
            if (isConnected) {
                request.setAttribute("connection", ldapConnector.getConnection());
                request.setAttribute("uid", username);
                request.getRequestDispatcher("/patients.jsp").forward(request,response);
            } else {
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
