package sdp.Model;

import javax.persistence.*;

/**
 * Created by Hp on 10/29/2017.
 */
@Entity
@Table(name = "test")
public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "age")
    long age;
    @Column(name = "height")
    double height;

    public TestModel() {
    }

    public TestModel(String name, long age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
