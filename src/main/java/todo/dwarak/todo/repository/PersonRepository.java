package todo.dwarak.todo.repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import todo.dwarak.todo.model.Person;
import todo.dwarak.todo.model.Todo;

public interface PersonRepository extends JpaRepository<Person, BigInteger>  {
    public Optional<Person> findByName(String name);

    @Query("select name from Todo t where t.personId = ?1")
    public Collection<Todo> getTodos(BigInteger personId);

    @Query(value = "select name from todo where person_id = ?", nativeQuery = true)
    public List<String> getTodosUsingNativeQuery(BigInteger personId);
}
