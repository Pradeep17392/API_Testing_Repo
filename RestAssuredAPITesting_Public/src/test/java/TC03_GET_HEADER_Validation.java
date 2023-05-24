
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_GET_HEADER_Validation {
	
	@Test
	void getAllHeaders()
	{
		//base URI
		RestAssured.baseURI = "http://localhost:3000/";
		
		//RequestType object
		RequestSpecification req = RestAssured.given();
		
		//'Response' class Object
		Response res = req.request(Method.GET,"users");
		
		//Headers
		Headers allHeaders = res.headers(); // Captures all the 'headers'
		
		//printing the headers values
		for(Header header:allHeaders)
		{
			System.out.println();
			System.out.println(header.getName()+": "+header.getValue());
		}
		System.out.println();
		
		//Assertions
		Assert.assertEquals(allHeaders.toString().contains("Express"), true);
		Assert.assertEquals(allHeaders.toString().contains("Origin, Accept-Encoding"), true);
		Assert.assertEquals(allHeaders.toString().contains("true"), true);
		Assert.assertEquals(allHeaders.toString().contains("no-cache"), true);
		Assert.assertEquals(allHeaders.toString().contains("-1"), true);
		Assert.assertEquals(allHeaders.toString().contains("nosniff"), true);
		Assert.assertEquals(allHeaders.toString().contains("application/json; charset=utf-8"), true);
		//Assert.assertEquals(allHeaders.toString().contains("756"), true);
		//Assert.assertEquals(allHeaders.toString().contains("W/\"2f4-dL2weiqh65qiCYfVZV7F0j39aIk\""), true);
		Assert.assertEquals(allHeaders.toString().contains("keep-alive"), true);
		Assert.assertEquals(allHeaders.toString().contains("timeout=5"), true);
	}

}
