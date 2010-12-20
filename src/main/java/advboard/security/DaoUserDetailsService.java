package advboard.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class DaoUserDetailsService implements UserDetailsService
{
    @Resource
    private advboard.data.UserDAO userDAO;

    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        advboard.data.User user = userDAO.getUserByName(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("No such user");
        }
        return new SecurityUser(username, user.getPassword(), user.getId(), user.getRoles());
    }
}
