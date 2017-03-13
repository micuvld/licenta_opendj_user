package actions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import user.AuthenticationStatus;
import user.NormalUser;
import user.User;
import utils.PropertyLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by root on 24.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        String requesterDn = "uid=doctor1,ou=NormalUsers,dc=spital,dc=moinesti,dc=com";
        String password = "qwerty";

        //PropertyLoader propertyLoader= new PropertyLoader();
        //System.out.println(System.getProperty("hostName"));

        User user = new NormalUser();
        String authenticationResponse = "";
        try {
            user.authenticate(requesterDn, password);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (user.getAuthenticationStatus() == AuthenticationStatus.APPROVED) {
            System.out.println("Authentication successful");
            List<String> patients = user.getPatients();


            System.out.println("Patients: ");
            for (int i = 0; i < patients.size(); ++i) {
                System.out.println(i + ". " + patients.get(i));
            }

            Scanner scanner = new Scanner(System.in);
            int indexOption = scanner.nextInt();

            String rightStatus = user.connectToPatient(patients.get(indexOption));
            System.out.println(rightStatus);

        } else {
            System.out.println("Authentication failed");
        }
    }

}
