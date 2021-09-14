package todo.dwarak.todo.controller;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.dwarak.todo.model.Person;
import todo.dwarak.todo.model.Todo;
import todo.dwarak.todo.service.PersonService;

@RestController
@RequestMapping("/person")
public class TodoRestController {

    public PersonService service;

    @Qualifier("personService")
    @Autowired
    public void setService(PersonService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String root() {
        return "application is runnning!";
    }

    @GetMapping("/all")
    public Iterable<Person> getAllPersons() {
        return service.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(final @PathVariable("id") BigInteger personId) {
        return service.getPersonById(personId);
    }

    @PostMapping("/{name}")
    public Optional<Person> createPerson(final @PathVariable String name) {
        return service.createPerson(name);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(final @PathVariable("id") BigInteger personId) {
        service.deletePerson(personId);
    }

    @GetMapping("/{id}/todos")
    public Iterable<Todo> getTodosInPerson(@PathVariable("id") BigInteger personId) {
        return service.getTodos(personId);
    }

    @DeleteMapping("/{id}/todos")
    public void deleteAllTodosInPerson(final @PathVariable("id") BigInteger personId) {
        service.deleteTodos(personId);
    }

    @PostMapping("/{id}/add")
    public Todo addTodoToPerson(final @PathVariable("id") BigInteger personId,
                                  final @RequestBody Todo todo) {
        return service.addTodo(personId, todo);
    }

    @GetMapping("/todos")
    public Iterable<Todo> getAllTodos() {
        return service.getTodos(null);
    }

    @PutMapping("/todos/{id}/move")
    public boolean moveTodoToDifferentPerson(@PathVariable("id") BigInteger todoId,
                                               @RequestParam("to_person") BigInteger toPersonId) {
        return service.moveTodo(todoId, toPersonId);
    }

    @DeleteMapping("/{id}/todos/{todo_id}")
    public void deleteFromPerson(final @PathVariable("id") BigInteger personId,
                                   final @PathVariable("todo_id") BigInteger todoId) {
        service.deleteTodo(personId, todoId);
    }

}
