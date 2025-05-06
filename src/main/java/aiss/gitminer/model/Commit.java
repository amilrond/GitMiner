package aiss.gitminer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Commit")
public class Commit {

    @Id
    private String id;

    @Column(name="title")
    @NotEmpty(message = "The name of the commit cannot be empty")
    private String title;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name="author_name")
    @NotEmpty(message = "Author name cannot be empty.")
    private String author_name;

    @Column(name="author_email")
    @Email
    private String author_email;

    @Column(name="authored_date")
    @NotEmpty(message = "Author date cannot be empty.")
    private String authored_date;

    @Column(name="web_url")
    @NotEmpty(message = "URL cannot be empty.")
    private String web_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Commit() {
    }

    public Commit(String id, String title, String message, String authorName, String authorEmail, String authoredDate, String webUrl) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.author_name = authorName;
        this.author_email = authorEmail;
        this.authored_date = authoredDate;
        this.web_url = webUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorName() {
        return author_name;
    }

    public void setAuthorName(String authorName) {
        this.author_name = authorName;
    }

    public String getAuthorEmail() {
        return author_email;
    }

    public void setAuthorEmail(String authorEmail) {
        this.author_email = authorEmail;
    }

    public String getAuthoredDate() {
        return authored_date;
    }

    public void setAuthoredDate(String authoredDate) {
        this.authored_date = authoredDate;
    }

    public String getWebUrl() {
        return web_url;
    }

    public void setWebUrl(String webUrl) {
        this.web_url = webUrl;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null) ? "<null>" : this.message));
        sb.append(',');
        sb.append("authorName");
        sb.append('=');
        sb.append(((this.author_name == null) ? "<null>" : this.author_name));
        sb.append(',');
        sb.append("authorEmail");
        sb.append('=');
        sb.append(((this.author_email == null) ? "<null>" : this.author_email));
        sb.append(',');
        sb.append("authoredDate");
        sb.append('=');
        sb.append(((this.authored_date == null) ? "<null>" : this.authored_date));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.web_url == null) ? "<null>" : this.web_url));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}

