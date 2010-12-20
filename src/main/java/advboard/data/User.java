package advboard.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles = EnumSet.noneOf(Role.class);

    public User()
    {
    }

    public User(String name, String pass)
    {
        this(name, pass, EnumSet.noneOf(Role.class));
    }

    public User(String name, String pass, EnumSet<Role> roles)
    {
        this.name = name;
        this.roles = roles;
        this.password = pass;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    public String getPassword()
    {
        return password;
    }
}
