package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.UsersBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;

public class AdminDataProviders {
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:/Users/SwapnaKannan/Documents/IBM/Manipal Projects/eLearningProject/Testdata.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "excel-inputs1")
	public Object[][] getExcelData1(){
		String fileName ="C:/Users/SwapnaKannan/Documents/IBM/Manipal Projects/eLearningProject/Testdata.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<UsersBean> list = new ELearningDAO().getUsers(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(UsersBean temp : list){
			Object[]  obj = new Object[6]; 
			obj[0] = temp.getFirstName(); 
			obj[1] = temp.getLastName(); 
			obj[2] = temp.getEmail();
			obj[3] = temp.getPhone();
			obj[4] = temp.getUserName();
			obj[5] = temp.getPwd();
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	

}
