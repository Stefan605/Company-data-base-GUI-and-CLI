import java.util.Scanner;
import java.util.function.Consumer;
import cli.Console;
import entity.Company;
import gui.Gui;
import dao.CompanyDAO;

import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String args[]) {

        /*Scanner input = new Scanner(System.in);
        Gson gson = new GsonBuilder().create();
        double num = input.nextInt();
    	
    	Company company = new Company();
        company.setId(1);
        company.setName("DHL");
        company.setIncome(56);
        company.setAddress("Street 1");
        CompanyDAO.updateCompany(company);

        List<Company> companyList = Arrays.asList(new Company("Nestle"), new Company("SAP"));

       // CompanyDAO.saveCompany(company);
       // CompanyDAO.saveCompanies(companyList);
        
        Company company1 = new Company(2,"company1","Boulevard Slivnitsa");
        CompanyDAO.saveCompany(company1);
        
        
        companyList = CompanyDAO.readCompanies(1);
        companyList.stream().forEach(new Consumer<Company>() {

			@Override
			public void accept(Company t) {
				System.out.println(gson.toJson(t));
				
			}
        	
        });*/
        
    	new Thread(new Gui()).start(); {
        	
        }
    	
    	new Thread(new Console()).start(); {
        	
        }

    }
}
