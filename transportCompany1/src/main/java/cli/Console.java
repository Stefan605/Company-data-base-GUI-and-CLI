package cli;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import dao.CompanyDAO;
import entity.Company;
import net.bytebuddy.asm.Advice.Exit;
public class Console implements Runnable{
	 
	
	private static final int READ_COMMAND = 1;
	private static final int CREATE_COMMAND = 2;
	private static final int UPDATE_COMMAND = 3;
	private static final int DELETE_COMMAND = 4;
	private static final int EXIT_COMMAND = 5;
	private static final int CUSTOM_UPDATE_COMMAND = 6;
	private static final int COPY_COMPANY = 7;
	private static final int CUSTOM_DELETE = 8;
	private static final int INCOME_RETURN = 9;
	private static final int SEARCH_BY_CRITERIA = 10;
	
	private static final String commandText = "Please enter a command to read:\n"
			+ "1 - read\n"
			+ "2 - create\n"
			+ "3 - update\n"
			+ "4 - delete\n"
			+ "5 - exit\n"
			+ "6 - custom update\n"
			+ "7 - copy company\n"
			+ "8 - custom delete\n"
			+ "9 - Show companies with an income higher than...\n"
			+ "10 - Search companies by chosen criteria\n"
			+ "Your choice: ";
	
	
	private static final String commandText11 = "Please choose criteria: \n"
			+"1 - income\n"
			+"2 - name\n"
			+"9 - build\n"
			+"Your choice: ";
	@Override
	public void run() {
		
				Scanner input = new Scanner (System.in);
				
		while(true) {
			 
			
			System.out.println(commandText);
			int choice = input.nextInt();
			input.nextLine();
			
			boolean shouldBreak = false;
			switch(choice) {
			case READ_COMMAND:
				readAction();
				break;
			
			case CREATE_COMMAND:
				System.out.println("Choose company name:");
			    String compName = input.nextLine();
			    input.nextLine();
			    System.out.println("Choose company address:");
			    String compAddress = input.nextLine();
			    
			    System.out.println("Choose company income:");
			    double income = input.nextInt();
			    input.nextLine();
			    
			    createAction(compName,compAddress,income);
				break;
			
			case UPDATE_COMMAND:
				System.out.println("Choose company ID:");
			    int id = input.nextInt();
			    input.nextLine();
			    
			    if(!CompanyDAO.idExists(id)) {
			    	System.out.println("The company doesn't exist");
			    	break;
			    }
			    Company company = CompanyDAO.returnCompany(id);
			    
			    System.out.println("Choose company name:");
			    String name = input.nextLine();
			    if(name.equals("")) {
			    	name = company.getName();
			    }
			    System.out.println("Choose company income:");
			    int companyIncome = input.nextInt();
			    System.out.println(companyIncome);
			    input.nextLine();
			   
			    System.out.println("Choose company address:");
			    String address = input.nextLine();
			    if(address.equals("")) {
			    	address = company.getAddress();
			    }
			    
			    
			    
				updateAction(id,name,companyIncome,address);
				break;
			
			case DELETE_COMMAND:
				System.out.println("ID of the company that you want to be deleted: ");
				int iD = input.nextInt();
				input.nextLine();
				deleteAction(iD);
				break;
			
			case EXIT_COMMAND:
				shouldBreak = true;
				break;
			case CUSTOM_UPDATE_COMMAND:
				System.out.println("Type the company IDs that you would like to modify."
						+ " Type -1 to proceed.");
				
				int companyId = 0;
				List<Integer> companyIds = new ArrayList<>();
				while(companyId!=-1) {
				 companyId = input.nextInt();
				 if(companyId==-1) {
					 break;
				 }
				 if(!CompanyDAO.idExists(companyId)) {
					 System.out.println("Please enter a valid company ID");
					 continue;
				 }
				 companyIds.add(companyId);
				}
				input.nextLine();
				System.out.println("Enter new address:");
				String companyAddress = input.nextLine();
				System.out.println("Enter new company income:");
				int companyincome = input.nextInt();
				input.nextLine();
				System.out.println("Enter new company name:");
				String companyName = input.nextLine();
				customUpdateAction(companyIds,companyName,companyincome,companyAddress);
				break;
			case COPY_COMPANY:
				System.out.println("ID to copy:");
				int idToCopy = input.nextInt();
				while(!CompanyDAO.idExists(idToCopy)) {
					System.out.println("Please enter a valid company ID");
					idToCopy = input.nextInt();
					input.nextLine();
				}
				input.nextLine();
				copyAction(idToCopy);
				break;
			case CUSTOM_DELETE:
				System.out.println("Type the company IDs that you would like to delete."
						+ " Type -1 to proceed.");
				int companiesToDelete = 0;
				List<Integer> companiesToDeleteList = new ArrayList<>();
				while(companiesToDelete!=-1) {
				 companiesToDelete = input.nextInt();
				 if(companiesToDelete==-1) {
					 break;
				 }
				 if(!CompanyDAO.idExists(companiesToDelete)) {
					 System.out.println("Please enter a valid company ID");
					 continue;
				 }
				 companiesToDeleteList.add(companiesToDelete);
				}
				customDeleteAction(companiesToDeleteList);
				break;
			case INCOME_RETURN:
				int chosenIncome = -1;
				System.out.println("Income: ");
				while(chosenIncome<0) {
				chosenIncome = input.nextInt();
				input.nextLine();
				}
				readByIncome(chosenIncome);
				break;
			
			case SEARCH_BY_CRITERIA:
				String choiceToString = "";
				System.out.println(commandText11);
				int choice11 = 0;
				int incomeChoice = 0;
				String nameChoice = "";
				
				while(choice11!=9 || choiceToString.isEmpty()) {
					
				choice11 = input.nextInt();
				input.nextLine();
				if(choice11==1) {
					System.out.println("Choose income: ");
					incomeChoice = input.nextInt();
					input.nextLine();
				}else if(choice11==2) {
					System.out.println("Type name: ");
					nameChoice = input.nextLine();
					
				}else {
					System.out.println("Please select a valid option.");
					System.out.println(commandText11);
					continue;
				}
				choiceToString += String.valueOf(choice11)+",";
				System.out.println(commandText11);
					
				}
				
				searchByCriteria(choiceToString,incomeChoice,nameChoice);
				break;
			
			default:
					break;
			}
			if(shouldBreak) {
				break;
			}
		}
		
	}
	
	public void readAction() {
		
		
		CompanyDAO.readCompanies().forEach(new Consumer<Company>() {

			@Override
			public void accept(Company t) {
				System.out.println(t);
				
			}
			
		});
	}
	
	public void createAction(String name, String address,double income) {
		CompanyDAO.createCompany(name, address, income);
		
	}

	public void updateAction(int id, String name, int income, String address) {
		System.out.println(CompanyDAO.updateCompany2(id, name, income, address));
	}

	public void deleteAction(int id) {
		System.out.println(CompanyDAO.deleteCompany(id));
	}
	
	public void customUpdateAction(List<Integer> ids,String name,int income,String address) {
		CompanyDAO.customUpdateCompany(ids, name, income, address);
	}
	
	public void copyAction(int id) {
		CompanyDAO.copyCompany(id);
	}
	
	public void customDeleteAction(List<Integer> companies) {
		CompanyDAO.customDeleteCompany(companies);
	}
	
	public void readByIncome(int income) {
		CompanyDAO.companyIncomeReturn(income).stream().forEach(new Consumer<Company>() {

			@Override
			public void accept(Company t) {
				System.out.println(t);
				
			}
			
		});
	}
	
	
	public void searchByCriteria(String criteria,int income,String name) {
		String text = "SELECT a FROM Company a WHERE ";
		if(criteria.contains("2")) {
			text+="name LIKE \'%"+name+"%\'";
			if(criteria.length()>2) {
				text+=" AND ";
			}
		}
		if(criteria.contains("1")) {
			text+="income >"+income;
		}
		CompanyDAO.searchCompaniesByCriteria(text).stream().forEach(new Consumer<Company>() {

			@Override
			public void accept(Company t) {
				System.out.println(t);
				
			}
			
		});
	}
	

}
