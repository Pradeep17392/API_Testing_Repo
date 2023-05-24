
import dataDrivenTest.XLUtils;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC06_GET_AllNodesData {
	
	void getAllJSONValues() 
	{
		//baseURI
		//RestAssured.baseURI = "http://localhost:3000/users";
		RestAssured.baseURI = "http://localhost:3000/";
		
		//Request Object
		RequestSpecification httpreq = RestAssured.given();
		
		//Response Object
		//Response httpres = httpreq.request(Method.GET,"4");
		Response httpres=httpreq.request(Method.GET,"users");
		
		int resCode = httpres.getStatusCode();
		
		//JSON Object
		JsonPath jsonpath = httpres.jsonPath();
		
		//Print all json nodes
		String subid = jsonpath.get("subjectID").toString();
		System.out.println("FirstName: "+jsonpath.get("firstName"));
		System.out.println("LastName: "+jsonpath.get("lastName"));
		System.out.println("SubjectID: "+subid);
		System.out.println("Id: "+jsonpath.get("id"));
	}
	
	@Test
	void getAllExcelValues() throws IOException
	{
		String xlpath = "C:/Users/Pradeep/Desktop/TestData/result.xlsx";
		
		int rowNum = XLUtils.getRowCount(xlpath, "Worksheet");
		int colNum = XLUtils.getCellCount(xlpath, "Worksheet", rowNum);
		
		String studdata[][]=new String[rowNum][colNum];
		
		for(int i=1;i<=rowNum;i++)
		{
			for(int j=0;j<colNum;j++)
			{
				studdata[i-1][j] = XLUtils.getCellData(xlpath, "Worksheet", i, j);
				System.out.println(studdata[i-1][j]);
				//System.out.println(studdata[0][0]);
			}
		}
		getAllJSONValues();
	}
}
