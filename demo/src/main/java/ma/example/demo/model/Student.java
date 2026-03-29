package ma.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotBlank(message = "Full name is mandatory")
  private String fullName;

  @NotBlank(message = "Email is mandatory")
  private String studentEmail;

  public Student() {
    super();
  }

  public long getId() { return id; }
  public void setId(long id) { this.id = id; }

  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }

  public String getStudentEmail() { return studentEmail; }
  public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
}