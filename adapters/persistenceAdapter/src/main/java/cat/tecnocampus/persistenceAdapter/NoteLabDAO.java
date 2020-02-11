package cat.tecnocampus.persistenceAdapter;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.ports.out.INoteLabDAO;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by josep on 8/9/17.
 */
@Repository
public class NoteLabDAO implements INoteLabDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_NOTE = "insert into note_lab (title, content, date_creation, date_edit, owner) values(?, ?, ?, ?, ?)";
    private final String FIND_ALL = "select * from note_lab";
    private final String FIND_BY_USERNAME = "select * from note_lab where owner = ? order by date_edit desc";
    private final String FIND_BY_TITLE = "select * from note_lab where title = ?";
    private final String FIND_BY_ID = "select * from note_lab where id = ?";
    private final String INSERT_USER_NOTES = "INSERT INTO note_lab (title, content, date_creation, date_edit, owner) values(?, ?, ?, ?, ?)";
    private final String UPDATE_NOTE = "update note_lab set title = ?, content = ?, date_edit = ? where date_creation = ? and title = ? and owner = ?";
    private final String DELETE_NOTE = "delete note_lab where title = ? and date_creation = ?";
    private final String EXISTS_NOTE = "select count(*) from note_lab where title = ? and date_creation = ?";

    private RowMapper<NoteLab> mapper = (resultSet, i) -> {
        NoteLab noteLab = new NoteLab.NoteLabBuilder(resultSet.getString("title"), resultSet.getString("content"))
                .dateCreation(resultSet.getTimestamp("date_creation").toLocalDateTime())
                .dateEdit(resultSet.getTimestamp("date_edit").toLocalDateTime())
                .build();
        return noteLab;
    };

    public NoteLabDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NoteLab> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public List<NoteLab> findByUsername(String username) {
        return jdbcTemplate.query(FIND_BY_USERNAME, new Object[]{username}, mapper);
    }

    @Override
    public NoteLab findById(int id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id},mapper);
    }


    @Override
    public int insert(NoteLab noteLab, UserLab userLab) {
        return jdbcTemplate.update(INSERT_NOTE, noteLab.getTitle(), noteLab.getContent(), Timestamp.valueOf(noteLab.getDateCreation()),
                Timestamp.valueOf(noteLab.getDateEdit()), userLab.getUsername());
    }

    @Override
    public int[] insertUserNotes(UserLab owner) {
        return jdbcTemplate.batchUpdate(INSERT_USER_NOTES, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                NoteLab note = owner.getNotesAsList().get(i);
                preparedStatement.setString(1, note.getTitle());
                preparedStatement.setString(2, note.getContent());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(note.getDateCreation()));
                preparedStatement.setTimestamp(4, Timestamp.valueOf(note.getDateEdit()));
                preparedStatement.setString(5, owner.getUsername());
            }

            @Override
            public int getBatchSize() {
                return owner.getNotes().size();
            }
        });
    }

    @Override
    public int updateNote(String oldTitle, NoteLab note, UserLab userLab) {
        return jdbcTemplate.update(UPDATE_NOTE,
                note.getTitle(), note.getContent(), note.getDateEdit(), note.getDateCreation(), oldTitle, userLab.getUsername());
    }

    @Override
    public int deleteNote(UserLab userLab, NoteLab note) {
        return jdbcTemplate.update(DELETE_NOTE, note.getTitle(), note.getDateCreation());
    }

    @Override
    public boolean existsNote(NoteLab note) {
        int countOfNotes = jdbcTemplate.queryForObject(
                EXISTS_NOTE, Integer.class, note.getTitle(), Timestamp.valueOf(note.getDateCreation()));
        return countOfNotes > 0;
    }

}
