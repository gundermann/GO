package de.nordakademie.wpk.todolist.core.api;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

	private static final long serialVersionUID = 2L;
	private Long id;
	private String title;
	private Date lastSync;
	private Integer position;
	private String comment;
	private Boolean status;
	private Date dateOfDue;
	private Date dateOfCompletion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getLastSync() {
		return lastSync;
	}

	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDateOfDue() {
		return dateOfDue;
	}

	public void setDateOfDue(Date dateOfDue) {
		this.dateOfDue = dateOfDue;
	}

	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

}
