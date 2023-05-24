
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_GET_Req {
	
	@Test
	void getUserDetails()
	{
		//baseURI
		RestAssured.baseURI = "http://localhost:3000/users";
		
		//RequestType object
		RequestSpecification req = RestAssured.given();
		
		//'Response' class Object
		Response res = req.request(Method.GET,"8");
		
		//Status code
		int resCode = res.getStatusCode();
		System.out.println();
		System.out.println("StatusCode for User Details: "+resCode);
		
		//print 'response' value
		String resBody = res.getBody().asString();
		System.out.println();
		System.out.println("Body for User Details: "+resBody+"\n");
		
		//Assertions
		Assert.assertEquals(resCode, 200);
		getSubjectDetails();
	}
	
	void getSubjectDetails()
	{
		//baseURI
		RestAssured.baseURI = "http://localhost:3000/subjects";
		
		//RequestType object
		RequestSpecification req = RestAssured.given();
		
		//'Response' class Object
		Response res = req.request(Method.GET,"8");
		
		//Status code
		int resCode = res.getStatusCode();
		System.out.println();
		System.out.println("StatusCode for Subject Details: "+resCode+"\n");
		
		//print 'response' value
		String resBody = res.getBody().asString();
		System.out.println("Body for Subject Details: "+resBody+"\n");
		
		//Assertions
		Assert.assertEquals(resCode, 200);
	}
	
}
