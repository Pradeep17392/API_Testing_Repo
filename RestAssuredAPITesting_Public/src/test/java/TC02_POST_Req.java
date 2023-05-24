//import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class TC02_POST_Req {

	@Test
	void postUserDetails()
	{
		//baseURI
		RestAssured.baseURI = "http://localhost:3000";
		
		//RequestType object
		RequestSpecification req = RestAssured.given();
		
		//'Response' class Object		
		req.header("Content-Type","application/json");
		
		JSONObject reqParams = new JSONObject();
		
		reqParams.put("firstName","Vernon");
		reqParams.put("lastName","Harper");
		reqParams.put("subjectID",8);
		reqParams.put("id",8);
		
		req.body(reqParams.toJSONString());
		
		Response res = req.request(Method.POST,"/users");
		
		//Status code
		int resCode = res.getStatusCode();
		System.out.println("Status Code: "+resCode);
		
		//print 'response' value
		String resBody = res.getBody().asString();
		System.out.println("Body: "+resBody+"\n");
		
		//Assertions
//		Assert.assertEquals(resCode, 201);
		
		
		//verification
		res.jsonPath().get("SuccessCode");
	}

}
