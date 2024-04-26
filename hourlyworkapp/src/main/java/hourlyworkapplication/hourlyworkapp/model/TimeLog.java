package hourlyworkapplication.hourlyworkapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class TimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timeLogId", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public Integer hours;

    @Column(nullable = true)
    public Integer minutes;

    @Column(nullable = false)
    public LocalDate logDate;
    
    @ManyToOne
    @JoinColumn(name = "userId", updatable = false)
    @JsonBackReference
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "workTypeId")
    @JsonBackReference
    private WorkType workType;

    public TimeLog() {}

    public TimeLog(String description, Integer hours, Integer minutes, LocalDate logDate, AppUser user, WorkType workType) {
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
        this.logDate = logDate;
        this.user = user;
        this.workType = workType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    @Override
    public String toString() {
        return "TimeLog [id=" + id + ", description=" + description + ", hours=" + hours + ", minutes=" + minutes
                + ", logDate=" + logDate + ", user=" + user + ", workType=" + workType + "]";
    }
}