package com.tasd.seekers;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SeekerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private String city;
	private Date birth;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<SkillEntity> skills;
	
	@Override
	public String toString() {
		return "SeekerEntity [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", city=" + city + ", birth=" + birth + ", skills=" + skills.toString() + "]";
	}

	public SeekerEntity() {
	}
	
	public SeekerEntity(String username, String firstName, String lastName, String city, Date birth) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.birth = birth;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public List<SkillEntity> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillEntity> skills) {
		this.skills = skills;
	}
	
	
	
}
