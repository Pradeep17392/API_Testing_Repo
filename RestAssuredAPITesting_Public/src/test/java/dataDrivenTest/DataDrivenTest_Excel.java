package dataDrivenTest;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_Excel {
	
	@Test
	(dataProvider="student")
	void postNewEmployee(String fname,String lname, String subid, String id)
	{
			//baseURI
			RestAssured.baseURI = "http://localhost:3000";
				
			//RequestType object
			RequestSpecification req = RestAssured.given();
	
			JSONObject reqParams = new JSONObject();
			
			reqParams.put("firstName",fname);
			reqParams.put("lastName",lname);
			reqParams.put("subjectID",subid);
			reqParams.put("id",id);
			
			//'Response' class Object		
			req.header("Content-Type","application/json");
			
			req.body(reqParams.toJSONString());
			
			Response res = req.request(Method.POST,"/users");
			
			String resBody=res.getBody().asString();
			
			System.out.println("Response body is: "+resBody);
	}
	
	@DataProvider (name="student") 
	String [][] getStudData() throws IOException
	{
		//String xlpath = System.getProperty("user.dir")+"/src/test/java/dataDrivenTest/testdata.xlsx";
		String xlpath = "C:/Users/Pradeep/Desktop/TestData/testdata.xlsx";
		
		int rowNum = XLUtils.getRowCount(xlpath, "Worksheet");
		int colNum = XLUtils.getCellCount(xlpath, "Worksheet", rowNum);
		
		String studdata[][]=new String[rowNum][colNum];
		
		for(int i=1;i<=rowNum;i++)
		{
			for(int j=0;j<colNum;j++)
			{
				studdata[i-1][j] = XLUtils.getCellData(xlpath, "Worksheet", i, j);
			}
		}
		return(studdata);
	}

}
