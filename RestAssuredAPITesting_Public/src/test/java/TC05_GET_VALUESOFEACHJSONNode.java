
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC05_GET_VALUESOFEACHJSONNode {
	
	@Test
	void getUserJSONValues() 
	{
		//baseURI
		RestAssured.baseURI = "http://localhost:3000/users";
		//RestAssured.baseURI = "http://localhost:3000/";
		
		//Request Object
		RequestSpecification httpreq = RestAssured.given();
		
		//Response Object
		Response httpres = httpreq.request(Method.GET,"4");
		//Response httpres=httpreq.request(Method.GET,"users");
		
		int resCode = httpres.getStatusCode();
		
		//JSON Object
		JsonPath jsonpath = httpres.jsonPath();
		
		//Print all json nodes
		String subid = jsonpath.get("subjectID").toString();
		System.out.println("Id: "+jsonpath.get("id"));
		System.out.println("FirstName: "+jsonpath.get("firstName"));
		System.out.println("LastName: "+jsonpath.get("lastName"));
		//System.out.println("SubjectID: "+subid);
		
		getSubJSONValues(subid);
		
		//Assertions
		Assert.assertEquals(resCode, 200);
		Assert.assertEquals(subid, "3");
	}
	
	void getSubJSONValues(String sid) 
	{
		String subid = sid;
		//baseURI
		RestAssured.baseURI = "http://localhost:3000/subjects";
		//RestAssured.baseURI = "http://localhost:3000/";
		
		//Request Object
		RequestSpecification httpreq = RestAssured.given();
		
		//Response Object
		Response httpres=httpreq.request(Method.GET,subid);
		//Response httpres=httpreq.request(Method.GET,"subjects");
		//Response httpResSub=httpres;
		
		//JSON Object
		JsonPath jsonpath = httpres.jsonPath();
		//JsonPath jsonpath = httpResSub.jsonPath();
				
		//Print all json nodes
		System.out.println("Subject ID: "+jsonpath.get("id")+" - "+jsonpath.get("name"));
	}
}
