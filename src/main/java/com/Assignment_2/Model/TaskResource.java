package com.Assignment_2.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskResource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	private String name;
	@Override
	public String toString() {
		return "TaskResource [taskId=" + taskId + ", name=" + name + ", description=" + description + ", createdBy="
				+ createdBy + ", startOn=" + startOn + ", closedOn=" + closedOn + ", state=" + state + ", id=" + id
				+ "]";
	}

	private String description;
	private String createdBy;
	private Date startOn;
	private Date closedOn;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getStartOn() {
		return startOn;
	}

	public void setStartOn(Date startOn) {
		this.startOn = startOn;
	}

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}


	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
