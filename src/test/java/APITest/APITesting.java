package APITest;

import APITest.BaseTest.BaseTestClass;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class APITesting extends BaseTestClass {

    @DataProvider(name = "data-per-page")
    Object[][] DataUsers(){
        String[][] Page = new String[][]{
                {"20"},
                {"5"},
                {"1"},
        };

        return Page;
    }

    @Test(dataProvider = "data-per-page")
    public void getDataPageCoount(String perPage){
        RequestSpecification request = RestAssured.given().filter(new AllureRestAssured());

        request.header("Content-Type", "application/json");
        Response response = request.get("/beers?page=2&per_page="+perPage);
        response.then().assertThat()
                .statusCode(200);
//        System.out.println("response data size : "+response.getBody().jsonPath().getList("$").size());
        Assert.assertEquals(Integer.parseInt(perPage), response.getBody().jsonPath().getList("$").size());
//        System.out.println("response time : "+response.time());
    }
}
