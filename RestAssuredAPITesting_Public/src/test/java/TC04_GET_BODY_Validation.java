
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC04_GET_BODY_Validation {
	
	@Test
	void getUserBodyDetails()
	{
		//baseURI
		RestAssured.baseURI = "http://localhost:3000/";
		//RestAssured.baseURI = "http://localhost:3000/users";
		
		//RequestType object
		RequestSpecification req = RestAssured.given();
		
		//'Response' class Object
		Response res = req.request(Method.GET,"users");
		//Response res = req.request(Method.GET,"1");
				
		//print 'response' value
		String resBody = res.getBody().asString();
		System.out.println();
		System.out.println("Body for User Details: "+resBody+"\n");
		
		//Assertions
		Assert.assertEquals(resBody.contains("Parvathy"), true);
		Assert.assertEquals(resBody.contains("kumar"), true);
		Assert.assertEquals(resBody.contains("4"), true);
	}
}
