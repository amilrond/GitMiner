package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    private String id;

    @Column(name = "body", columnDefinition = "TEXT")
    @NotEmpty(message = "The message cannot be empty.")
    private String body;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;


    @Column(name = "created_at")
    @NotEmpty(message = "The field created_at cannot be empty.")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public Comment() {

    }

    public Comment(String id, String body, User author, String created_at, String updated_at) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.created_at = created_at;
        this.updated_at = updated_at;

    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    @JsonProperty("created_at")
    public String getCreatedAt() { return created_at; }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) { this.created_at = createdAt; }

    @JsonProperty("updated_at")
    public String getUpdatedAt() { return updated_at; }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) { this.updated_at = updatedAt; }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public Issue getIssue() { return issue; }

    public void setIssue(Issue issue) { this.issue = issue; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null) ? "<null>" : this.body));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null) ? "<null>" : this.author));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.created_at == null) ? "<null>" : this.created_at));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updated_at == null) ? "<null>" : this.updated_at));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.issue == null) ? "<null>" : this.issue));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

