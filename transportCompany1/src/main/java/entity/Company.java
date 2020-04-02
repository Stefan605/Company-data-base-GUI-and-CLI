package entity;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="address", nullable = false)
    private String address;
    
    @Column(name="income", nullable = false)
    private Double income;

    public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Company() {
    }

    public Company(String name) {
        this.name = name;
    }
    

    public Company(long id, String name,double income, String address) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.address = address;
    }

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", income=" + income + "]";
	}

    /*@Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }*/
    
    
}
