package cat.tecnocampus.ports.out;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.UserLab;

import java.util.List;

public interface INoteLabDAO {
    List<NoteLab> findAll();

    List<NoteLab> findByUsername(String username);

    NoteLab findById(int id);

    int insert(NoteLab noteLab, UserLab userLab);

    int[] insertUserNotes(UserLab owner);

    int updateNote(String oldTitle, NoteLab note, UserLab userLab);

    int deleteNote(UserLab userLab, NoteLab note);

    boolean existsNote(NoteLab note);
}
