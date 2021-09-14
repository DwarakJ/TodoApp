package todo.dwarak.todo.repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import todo.dwarak.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, BigInteger>  {
    public Optional<Todo> findByName(String name);

    Collection<Todo> findByPersonId(BigInteger personId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Todo t where t.personId = ?1")
    void deleteByPersonId(BigInteger personId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Todo t where t.personId = ?1 and t.id = ?2")
    public void delete(BigInteger personId, BigInteger todoId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Todo t set t.personId = ?2 where t.id = ?1")
    public int updatePerson(BigInteger todoId, BigInteger personId);
}
