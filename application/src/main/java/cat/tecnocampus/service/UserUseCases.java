package cat.tecnocampus.service;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.ports.in.IUserUseCases;
import cat.tecnocampus.ports.out.INoteLabDAO;
import cat.tecnocampus.ports.out.IUserLabDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by roure on 14/09/2017.
 *
 * All methods update the database
 */
@Service("userUseCases")
public class UserUseCases implements IUserUseCases {
	
    private final INoteLabDAO noteLabDAO;
    
    private final IUserLabDAO userLabDAO;

    public UserUseCases(INoteLabDAO NoteLabDAO, IUserLabDAO UserLabDAO) {
        this.noteLabDAO = NoteLabDAO;
        this.userLabDAO = UserLabDAO;
    }

    @Override
    public UserLab createUser(String username, String name, String secondName, String email) {
        UserLab userLab = new UserLab.UserLabBuilder(username, email).name(name).secondName(secondName).build();
        registerUser(userLab);
        return userLab;
    }

    //The @Transactiona annotation states that saveUser is a transaction. So ,if a unchecked exception is signaled
    // (and not cached) during the saveUser method the transaction is going to rollback
    @Override
    @Transactional
    public int registerUser(UserLab userLab) {
        return userLabDAO.insert(userLab);
    }

    @Override
    public int deleteUser(String username) {
        return userLabDAO.delete(username);
    }

    @Override
    public NoteLab addUserNote(UserLab userLab, String title, String contents) {
        LocalDateTime now = LocalDateTime.now();
        NoteLab note = new NoteLab.NoteLabBuilder(title, contents).dateEdit(now).dateCreation(now).build();
        userLab.addNote(note);
        noteLabDAO.insert(note, userLab);
        return note;
    }

    @Override
    public NoteLab addUserNote(UserLab userLab, NoteLab noteLab) {
        userLab.addNote(noteLab);
        noteLabDAO.insert(noteLab, userLab);

        return noteLab;
    }

    @Override
    public NoteLab createUserNote(UserLab userLab, NoteLab noteLab) {
        noteLab.setDateCreation(LocalDateTime.now());
        noteLab.setDateEdit(LocalDateTime.now());

        return addUserNote(userLab, noteLab);
    }

    @Override
    public NoteLab updateUserNote(UserLab userLab, NoteLab oldNote, String title, String contents) {
        NoteLab newNote = new NoteLab.NoteLabBuilder(title, contents)
                .dateCreation(oldNote.getDateCreation()).dateEdit(LocalDateTime.now()).build();

        userLab.removeNote(oldNote.getTitle());

        userLab.addNote(newNote);
        noteLabDAO.updateNote(oldNote.getTitle(), newNote, userLab);
        return newNote;
    }

    @Override
    public NoteLab updateUserNote(UserLab user, NoteLab note, String oldNoteTitle) {

        NoteLab queryNote = new NoteLab.NoteLabBuilder(oldNoteTitle, "").dateCreation(note.getDateCreation()).build();
        NoteLab oldNote = user.getNote(queryNote);
        note.setDateCreation(oldNote.getDateCreation());
        note.setDateEdit(LocalDateTime.now());

        noteLabDAO.updateNote(oldNoteTitle, note, user);

        return note;
    }

    @Override
    public NoteLab deleteUserNote(UserLab user, NoteLab note) {
        noteLabDAO.deleteNote(user, note);
        return note;
    }

    @Override
    public List<NoteLab> getUserNotes(String userName) {
        return noteLabDAO.findByUsername(userName);
    }

    //Note that users don't have their notes with them
    @Override
    public List<UserLab> getUsers() {
        return userLabDAO.findAll();
    }

    @Override
    public UserLab getUser(String userName) {
        return userLabDAO.findByUsername(userName);
    }

    @Override
    public List<NoteLab> getAllNotes() {
        return noteLabDAO.findAll();
    }

    @Override
    public List<NoteLab> getUserNotes(UserLab userLab) {
        if (userLab.getNotes().isEmpty()) {  //in case user has been retrieved lazily
            userLab.addNotes(noteLabDAO.findByUsername(userLab.getUsername()));
        }
        return userLab.getNotesAsList();
    }

}
