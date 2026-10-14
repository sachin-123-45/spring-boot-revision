package com.sachin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task {
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


@NotBlank(message="Title cannot blank")
@Size(max= 100 , message="title cannot be exceed 100 characters")
	private String title;

@Size(max = 500, message = "Description cannot exceed 500 characters")
	private String description;
	private boolean completed;
	

public Task()
{
	
}

public String getTitle() {
	return title;
}




public void setTitle(String title) {
	this.title = title;
}




public String getDescription() {
	return description;
}




public void setDescription(String description) {
	this.description = description;
}




public boolean isCompleted() {
	return completed;
}




public void setCompleted(boolean completed) {
	this.completed = completed;
}

}