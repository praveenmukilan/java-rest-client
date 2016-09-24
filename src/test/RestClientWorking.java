package test;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class RestClientWorking {
	
	static String accessToken="";
	
//	https://core-staging.honestbee.com/api/account/token
	
	public RestClientWorking() {
		// TODO Auto-generated constructor stub
		RestAssured.baseURI="https://core-staging.honestbee.com";
	}
	
	
	public static String getAccessToken(){
		RestAssured.baseURI="https://core-staging.honestbee.com";
		Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
		 Map<String,String> bodyParams = new HashMap<>();
		 bodyParams.put("username", "praveen.ms+cpanel@honestbee.com");
		 bodyParams.put("password", "password");
		 bodyParams.put("grant_type", "password");
		 bodyParams.put("client_id", "ad2a56a5e13f6f2455f70844aeadd8b9838c1afcbc206af7509500143141899c");
		 bodyParams.put("client_secret", "8212dcc268a0e0e6b6ae10a88fd65d96fdcd2f884ad4de840f3a159d9f40e6f1");
		 bodyParams.put("Content-Type", "x-www-form-urlencoded");
		 

		
//		System.out.println(given()  
//		.body(bodyParams)
//		.request().urlEncodingEnabled(true)
//		.when().toString());
		Response response = 
		given() 
//		.header(header)
		.contentType(ContentType.JSON)
		.body(bodyParams)
//		.header(header)
		.request()
        .post("/api/account/token")
        ;
		System.out.println(response.asString());
		
		String accessToken= response.path("access_token");
		System.out.println(accessToken);
		return accessToken;
	}
	
	public static String getBatches(int beeId, String access_token){
		RestAssured.baseURI="https://core-staging.honestbee.com";
		Response resp = given()
				.contentType(ContentType.JSON)
				.header("Accept","application/vnd.honestbee+json;version=2")
				.header("Accept-Language","en-SG")
				.queryParam("access_token", access_token)
				.get("/api/bees/"+beeId+"/batches");
		
		
		System.out.println(resp.asString());
				return resp.asString();
		
	}
	
	public static String getFulfillmentDetails(String access_token,int fulfillmentId){
		RestAssured.baseURI="https://core-staging.honestbee.com";
//		https://core-bee-staging.honestbee.com/api/order_fulfillments/10837?access_token=6b2a4e5c213d2d2d6a5970bbc065cb0ecff9edafd8076b7f35bad28b79af6218&role=shopper
		Response resp = given()
				.contentType(ContentType.JSON)
				.header("Accept","application/vnd.honestbee+json;version=2")
				.header("Accept-Language","en-SG")
				.queryParam("access_token", access_token)
				.queryParam("role", "shopper")
				.get("/api/order_fulfillments/"+fulfillmentId);
		
		
		System.out.println(resp.asString());
				return resp.asString();
		
	}
	
	public static void main(String args[]){
		accessToken=getAccessToken();
//		getBatches(7, accessToken);
		getFulfillmentDetails(accessToken,10837);
	}
	

}
