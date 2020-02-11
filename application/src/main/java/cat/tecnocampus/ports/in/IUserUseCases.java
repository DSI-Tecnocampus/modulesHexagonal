package cat.tecnocampus.ports.in;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.UserLab;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserUseCases {
    UserLab createUser(String username, String name, String secondName, String email);

    //The @Transactiona annotation states that saveUser is a transaction. So ,if a unchecked exception is signaled
    // (and not cached) during the saveUser method the transaction is going to rollback
    @Transactional
    int registerUser(UserLab userLab);

    int deleteUser(String username);

    NoteLab addUserNote(UserLab userLab, String title, String contents);

    NoteLab addUserNote(UserLab userLab, NoteLab noteLab);

    NoteLab createUserNote(UserLab userLab, NoteLab noteLab);

    NoteLab updateUserNote(UserLab userLab, NoteLab oldNote, String title, String contents);

    NoteLab updateUserNote(UserLab user, NoteLab note, String oldNoteTitle);

    NoteLab deleteUserNote(UserLab user, NoteLab note);

    List<NoteLab> getUserNotes(String userName);

    //Note that users don't have their notes with them
    List<UserLab> getUsers();

    UserLab getUser(String userName);

    List<NoteLab> getAllNotes();

    List<NoteLab> getUserNotes(UserLab userLab);
}
