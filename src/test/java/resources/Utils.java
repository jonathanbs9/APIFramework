package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if (req == null) {
            /*** PrinStream requires filepath ***/
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            //RestAssured.baseURI="https://rahulshettyacademy.com";
            String baseURLEnv = getGlobalValues("baseURL");
            //System.out.println("env Var baseURL => "+ baseURLEnv);

            req = new RequestSpecBuilder()
                    .setBaseUri(baseURLEnv)
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

            return req;
        }
        return req;
    }

    public static String getGlobalValues(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("D:\\Proyectos\\udemy\\APIFramework\\src\\test\\java\\resources\\global.properties");

        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public String getJSONPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
