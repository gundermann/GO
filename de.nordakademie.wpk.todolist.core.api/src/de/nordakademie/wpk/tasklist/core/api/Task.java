package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Klasse f�r Taskobjekte. Diese sind Elemente einer Taskliste.
 * @author Kathrin Kurtz
 *
 */
public class Task implements Serializable {

	private static final long serialVersionUID = 4L;
	private String id;
	private String title;
	private Date lastSync;
	private Integer position;
	private String comment;
	private Boolean status;
	private Date dateOfDue;
	private Date dateOfCompletion;

	/**
	 * Gibt die ID der Task zur�ck.
	 * @return
	 */
	public String getId() {
		return id == null ? "" : id;
	}

	/**
	 * Setzt die ID der Task.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gibt den Titel der Task zur�ck.
	 * @return
	 */
	public String getTitle() {
		return title == null ? "" : title;
	}

	/**
	 * Setzt den Titel der Task.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gibt das letzte Synchronisierungsdatum mit dem Server zur�ck.
	 * @return
	 */
	public Date getLastSync() {
		return lastSync;
	}

	/**
	 * Setzt das letzte Synchronisierungsdatum mit dem Server.
	 * @param lastSync
	 */
	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	/**
	 * Gibt die Position der Task in der Taskliste zur�ck.
	 * @return
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * Setzt die Position der Task innerhalb der Taskliste.
	 * @param position
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * Gibt die Beschreibung der Task zur�ck.
	 * @return
	 */
	public String getComment() {
		return comment == null ? "" : comment;
	}

	/**
	 * Setzt die Beschreibung der Task.
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gibt true zur�ck wenn die Task als erledigt markiert wurde.
	 * @return
	 */
	public Boolean getStatus() {
		return status == null ? false : status;
	}

	/**
	 * Setzt den Status. True bedeutet das die Task erledigt ist.
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * Gibt das F�lligkeitsdatum der Task zur�ck.
	 * Gibt null zur�ck wenn kein F�lligkeitsdatum gesetzt ist.
	 * @return
	 */
	public Date getDateOfDue() {
		return dateOfDue;
	}

	/**
	 * Setzt das F�lligkeitsdatum der Task.
	 * Null bedeutet es gibt kein F�lligkeitsdatum.
	 * @param dateOfDue
	 */
	public void setDateOfDue(Date dateOfDue) {
		this.dateOfDue = dateOfDue;
	}

	/**
	 * Gibt das Datum der Fertigstellung der Task zur�ck.
	 * Null wenn die Task noch nicht abgeschlossen ist.
	 * @return
	 */
	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}
	
	/**
	 * Setzt das Fertigstellungsdatum der Task.
	 * Null bedeutet, dass die Task noch nicht fertiggestellt ist.
	 * @param dateOfCompletion
	 */
	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

}
