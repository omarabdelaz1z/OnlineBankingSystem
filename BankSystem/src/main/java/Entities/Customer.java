package Entities;

public class Customer {
	private String customerID;
	private String name;
	private String address;
	private String phoneNumber;
	private String PIN;
	private Account customerAccount;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCustomerAccount(Account customerAccount){
		this.customerAccount = customerAccount;
	}

	public Account getCustomerAccount() {
		return customerAccount;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", PIN=" + PIN;
	}
}
