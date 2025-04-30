package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "GMUser")     // Watch out: User is a reserved keyword in H2
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    @NotEmpty(message = "The username cannot be empty")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "web_url")
    private String webUrl;

    @OneToMany(mappedBy = "author")
    private List<Comment> authoredComments;

    @OneToMany(mappedBy = "author")
    private List<Issue> authoredIssues;

    @OneToMany(mappedBy = "assignee")
    private List<Issue> assignedIssues;

    public User() {
    }

    public User(String username, String name, String avatarUrl, String webUrl) {
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.webUrl = webUrl;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAvatarUrl() { return avatarUrl; }

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getWebUrl() { return webUrl; }

    public void setWebUrl(String webUrl) { this.webUrl = webUrl; }

    public List<Comment> getAuthoredComments() { return authoredComments; }

    public void setAuthoredComments(List<Comment> authoredComments) { this.authoredComments = authoredComments; }

    public List<Issue> getAuthoredIssues() { return authoredIssues; }

    public void setAuthoredIssues(List<Issue> authoredIssues) { this.authoredIssues = authoredIssues; }

    public List<Issue> getAssignedIssues() { return assignedIssues; }

    public void setAssignedIssues(List<Issue> assignedIssues) { this.assignedIssues = assignedIssues; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null) ? "<null>" : this.username));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        sb.append("avatarUrl");
        sb.append('=');
        sb.append(((this.avatarUrl == null) ? "<null>" : this.avatarUrl));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.webUrl == null) ? "<null>" : this.webUrl));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

