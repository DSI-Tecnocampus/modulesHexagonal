package cat.tecnocampus.ports.out;

import cat.tecnocampus.domain.UserLab;

import java.util.List;

public interface IUserLabDAO {
    int insert(UserLab userLab);

    List<UserLab> findAllEager();

    List<UserLab> findAll();

    UserLab findByUsername(String userName);

    int delete(String username);
}
