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


public class SingleUserAPI {

    @Test
    public void GetBooksDetails() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RestAssured.baseURI = Common.baseUrl;


        String SingleUSerActualResponse = given().log().all().request(Method.GET, "/api/users/2").asString();


        String singleUserAPIResponse = given().log().all().request(Method.GET, "/api/users/2").asString();

        SingleUserPOJO actualResponseobj = given().log().all().request(Method.GET, "/api/users/2").as(SingleUserPOJO.class);



        try(FileWriter file = new FileWriter("src/test/java/Data/ActualData/SingleUser.txt")){
            file.write(new Gson().toJson(actualResponseobj));
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }






        //SingleUserPOJO actualResponseObj = mapper.readValue(SingleUSerActualResponse, SingleUserPOJO.class);


        SingleUserPOJO expectedResponse = mapper.readValue(new File("src/test/java/Data/ExpectedData/SingleUser.json"), SingleUserPOJO.class);
        String expected = new Gson().toJson(expectedResponse);
        System.out.println(expected);
        assertEquals(actualResponseobj.getData().getEmail(),expectedResponse.getData().getEmail());
        assertEquals(actualResponseobj.getData().getAvatar(),expectedResponse.getData().getAvatar());

    }
}
