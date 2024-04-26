package hourlyworkapplication.hourlyworkapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;;

@Entity
public class WorkType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    public String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workType")
    private List<TimeLog> timeLogList;

    public WorkType(){}

    public WorkType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TimeLog> getTimeLogList() {
        return timeLogList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeLogList(List<TimeLog> timeLogList) {
        this.timeLogList = timeLogList;
    }

    @Override
    public String toString() {
        return "WorkType [id=" + id + ", name=" + name + "]";
    }
}
