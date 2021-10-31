package my.test.token.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.test.token.service.entity.User;
import my.test.token.service.enums.Status;

/**
 * @Author Sanuja_G
 *   @CreatedTime on 19-10-2021 09:15:31 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByUsernameAndStatus(String username, Status status);
}
