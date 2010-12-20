package advboard.data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "advertisements")
public class Advertisement implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String message;

    private String tags;

    private java.sql.Timestamp creationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User author;

    public Advertisement()
    {
    }

    public Advertisement(String message, String tags, User author)
    {
        this.message = message;
        this.tags = tags;
        this.author = author;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setCreationDate()
    {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public User getAuthor()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }
}
