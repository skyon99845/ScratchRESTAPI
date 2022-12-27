package APIRequest;

import Common.Common;
import POJO.SingleUserPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import org.testng.annotations.Test;

import javax.swing.text.html.parser.Parser;
import javax.xml.bind.JAXBElement;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class SingleUser {

    @Test
    public void GetBooksDetails() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = Common.baseUrl;
        // Get the RequestSpecification of the request to be sent to the server.
        //RequestSpecification httpRequest = RestAssured.given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        //Response response = httpRequest.request(Method.GET, "/api/users/2");
        // Print the status and message body of the response received from the server
//        System.out.println("Status received => " + response.getStatusLine());
//        System.out.println("Response=>" + response.prettyPrint());

        //String responseData =response.asString();
        //System.out.println(responseData);

        String SingleUSerActualResponse = given().log().all().request(Method.GET, "/api/users/2").asString();
        System.out.println(SingleUSerActualResponse);

        String singleUserAPIResponse = given().log().all().request(Method.GET, "/api/users/2").asString();
//        System.out.println(singleUserAPIResponse.statusCode());
//        System.out.println(singleUserAPIResponse.statusLine());
        SingleUserPOJO actualResponseobj = given().log().all().request(Method.GET, "/api/users/2").as(SingleUserPOJO.class);

        //System.out.println();

//        System.out.println("Accessing the Records");
//        System.out.println("Printing the token Data: "+actualResponseobj.getData().getFirst_name());
//        System.out.println("Printing the token Support"+actualResponseobj.getSupport());

        String fileName = "src/test/java/Data/ActualData/SingleUser.txt";

        Path path = Paths.get(fileName);

//        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//            JsonElement tree = gson.toJsonTree(SingleUSerActualResponse);
//            gson.toJson(tree, writer);
//        }
//        System.out.println("Printing into the file successfull");
//        System.out.println("SingleUserResponse written to file");
        try(FileWriter file = new FileWriter("src/test/java/Data/ActualData/SingleUser.txt")){
            file.write(new Gson().toJson(actualResponseobj));
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }






        //SingleUserPOJO actualResponseObj = mapper.readValue(SingleUSerActualResponse, SingleUserPOJO.class);


        SingleUserPOJO expectedResponse = mapper.readValue(new File("src/test/java/Data/ExpectedData/SingleUser.json"), SingleUserPOJO.class);
//        System.out.println("Printing the ID of from expected response:"+expectedResponse.getData().getId());
//        System.out.println("Printing the Email from the expected response:"+expectedResponse.getData().getEmail());
//
//        System.out.println("Printing the ID from the actual response:"+actualResponseObj.getData().getId());
//        System.out.println("Printing the Email from the actual response:"+actualResponseObj.getData().getEmail());
//


        String expected = new Gson().toJson(expectedResponse);
        System.out.println(expected);

        //assertEquals(parser.parse(string1), parser.parse(string2))


        assertEquals(actualResponseobj.getData().getEmail(),expectedResponse.getData().getEmail());
        assertEquals(actualResponseobj.getData().getAvatar(),expectedResponse.getData().getAvatar());




        //Assert.assertEquals(actualResponseObj.getData().getEmail(),expectedResponse.getData().getEmail());


    }
}
