package com.lsolution.Library_demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="projects")
public class Projects {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="userid")
    private String userid;

    @Column(name="name")
    private String pname;
    
    @Column(name="descriptions")
    private String description;

    @Column(name="lang")
    private String lan;

    @Column(name="link")
    private String link;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fileid")
    private ProjectFile file;
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ProjectFile getFile() {
		return file;
	}

	public void setFile(ProjectFile file) {
		this.file = file;
	}

	public Projects(){

    }

	public Projects(String userid, String pname, String description, String lan, String link, ProjectFile file) {
		this.userid = userid;
		this.pname = pname;
		this.description = description;
		this.lan = lan;
		this.link = link;
		this.file = file;
	}

}
