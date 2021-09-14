package todo.dwarak.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.dwarak.todo.exception.PersonNotFoundException;
import todo.dwarak.todo.exception.TodoNotFoundException;
import todo.dwarak.todo.model.Person;
import todo.dwarak.todo.model.Todo;
import todo.dwarak.todo.repository.PersonRepository;
import todo.dwarak.todo.repository.TodoRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Service("personService")
public class PersonService {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private TodoRepository todoRepo;

    public Iterable<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public Person getPersonById(BigInteger personId) {
        return getPerson(personId);
    }

    public Optional<Person> createPerson(String name) {
        Person person = new Person();
        person.setName(name);
        person.setCreatedOn(new Date());
        return Optional.of(personRepo.save(person));
    }

    public void deletePerson(BigInteger personId) {
        Person person = getPerson(personId);
        person.setId(personId);
        personRepo.delete(person);
    }

    public Iterable<Todo> getTodos(BigInteger personId) {
        if (personId == null) {
            return todoRepo.findAll();
        }
        personRepo.getTodos(personId);
        Person person = getPerson(personId);
        return personRepo.getTodos(person.getId());
    }

    public void deleteTodos(BigInteger personId) {
        Person person = getPerson(personId);
        todoRepo.deleteByPersonId(person.getId());
    }

    public Todo addTodo(BigInteger personId, Todo todo) {
        Person person = getPerson(personId);
        todo.setPersonId(person.getId());
        todo.setCreatedOn(new Date());
        return todoRepo.save(todo);
    }

    public boolean moveTodo(BigInteger todoId, BigInteger toPersonId) {
        Todo todo = getTodo(todoId);
        Person person = getPerson(toPersonId);
        return 1 == todoRepo.updatePerson(todo.getId(), person.getId());
    }

    public void deleteTodo(BigInteger personId, BigInteger todoId) {
        Todo todo = getTodo(todoId);
        todoRepo.delete(personId, todo.getId());
    }

    private Person getPerson(final BigInteger personId) {
        return personRepo.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));
    }

    private Todo getTodo(final BigInteger todoId) {
        return todoRepo.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
    }

}

