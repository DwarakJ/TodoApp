package todo.dwarak.todo.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "todo")
@NamedNativeQuery(name = "todoByPersonId", query = "select id, name, person_id, created_on from todo t where t.person_id = ?", resultClass = Todo.class)
public class Todo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger id;

    @Column(name = "person_id")
    @JsonProperty("person_id")
    private BigInteger personId;

    private String name;

    @Column(name = "created_on")
    @JsonProperty("created_on")
    private Date createdOn;

}
