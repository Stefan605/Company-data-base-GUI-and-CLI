package dao;

import configuration.SessionFactoryUtil;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyDAO {
	
	
	
	public static void createCompany(String name, String address, double income) {
		Company company = new Company();
		company.setAddress(address);
		company.setName(name);
		company.setIncome(income);
		try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(company);
            
		}
	}

    public static void saveCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
        }
    }

    public static void saveCompanies(List<Company> companyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companyList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Company> readCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Company a", Company.class).getResultList();
        }
    }
    
    public static List<Company> readCompanies(String nameCriteria) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Company a WHERE name=:name", Company.class)
            		.setParameter("name","DHL")
            		.getResultList();
            		
        }
        
    }
    
    public static List<Company> readCompanies(double income) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Company a WHERE income >"+income, Company.class).getResultList();
            		
        }
        
    }
    
    public static List<Company> searchCompanies(String substring){
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Company a WHERE name LIKE \'%"+substring+"%\'", Company.class).getResultList();
            		
        }
    }
    
    public static List<Company> searchCompaniesByCriteria(String criteria){
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(criteria, Company.class).getResultList();
            		
        }
    }
    
    public static Company returnCompany(int id) {
    	
    	if(idExists(id)) {
    		try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                return session.createQuery("SELECT a FROM Company a WHERE id="+id, Company.class).getSingleResult();
    		}
    		
    		
    	}
    	return null;
    }
    
    
    public static List<Company> companyIncomeReturn(int income) {
    	
    		try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                return session.createQuery("SELECT a FROM Company a WHERE income>"+income, Company.class).getResultList();
    		}
    }
    
    public static void updateCompany(Company company) {
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(company);
            transaction.commit();
        }
    }
    
    public static boolean idExists(int id) {
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            if(session.createQuery("SELECT a FROM Company a WHERE id="+id, Company.class).getResultList().size()!=1) {
            	return false;
            }
            return true;
    	}
    }
    	
    public static String updateCompany2(int id, String name, int income, String address) {
    	 if(!idExists(id)) {
         	return "No such company. Please choose a valid company ID";
         }
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                      
            Company company = new Company(id,name,income,address);
            updateCompany(company);
            
            return "Company ID: "+id+" has been updated.";
            
		}
    }
    
    public static void customUpdateCompany(List<Integer> ids, String name, int income, String address) {
    	for(int id : ids) {
    		updateCompany2(id, name, income, address);
    	}
    }
    
    public static void copyCompany(int id) {
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
             Company company = returnCompany(id);
             
             createCompany(company.getName(),company.getAddress(),company.getIncome());
             System.out.println("Company has been copied.");
            }
    	  
    }
    
    public static String deleteCompany(int id) {
    	if(!idExists(id)) {
    		return "No such company. Please choose a valid company ID";
    	}
    	try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.createQuery("SELECT a FROM Company a WHERE id="+id, Company.class).getSingleResult();
            session.delete(company);
            transaction.commit();
            return "Company ID '"+id+"' has been deleted.";
    	}
    }
    
    public static void customDeleteCompany(List<Integer> companies) {
    	for(int id : companies) {
    		System.out.println(deleteCompany(id));
    	}
    }
}
