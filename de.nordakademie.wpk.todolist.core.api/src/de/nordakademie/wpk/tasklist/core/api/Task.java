package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

	private static final long serialVersionUID = 4L;
	private String id;
	private String title;
	private Date lastSync;
	private Long position;
	private String comment;
	private Boolean status;
	private Date dateOfDue;
	private Date dateOfCompletion;

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title == null ? "" : title;
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

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getComment() {
		return comment == null ? "" : comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getStatus() {
		return status == null ? false : status;
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
