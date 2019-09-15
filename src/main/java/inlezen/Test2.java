package inlezen;

import com.google.gson.Gson;

/**
 * Java Program to map a Java object to JSON String using Gson library.
 * 
 * @author WINDOWS 8
 *
 */
public class Test2 {

	public static void main(String args[]) {

		UserDetails user = new UserDetails("John", "john.doe@gmail.com", 29, 5168161922L, "NewYork", false);

		Gson gson = new Gson();

		String json = gson.toJson(user);

		System.out.println(json);

		UserDetails user2 = gson.fromJson(json, UserDetails.class);
		System.out.println(user2.toString());

	}

}

class UserDetails {

	private String name;
	private String email;
	private int age;
	private long phone;
	private String city;
	private boolean hasCreditCard;

	public UserDetails(String name, String email, int age, long phone, String city, boolean hasCreditCard) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.phone = phone;
		this.city = city;
		this.hasCreditCard = hasCreditCard;
	}

	public UserDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isHasCreditCard() {
		return hasCreditCard;
	}

	public void setHasCreditCard(boolean hasCreditCard) {
		this.hasCreditCard = hasCreditCard;
	}

}
