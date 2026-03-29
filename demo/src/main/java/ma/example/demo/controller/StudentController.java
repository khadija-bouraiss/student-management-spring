package ma.example.demo.controller;

import ma.example.demo.model.Student;
import jakarta.validation.Valid;
import ma.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

  @Autowired
  private StudentRepository studentRepository;

  @GetMapping("/register")
  public String showSignUpForm(Student student) {
    return "add-student";
  }

  @PostMapping("/addstudent")
  public String addStudent(@Valid Student student, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "add-student";
    }
    studentRepository.save(student);
    model.addAttribute("students", studentRepository.findAll());
    return "index";
  }

  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") long id, Model model) {
    Student student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    model.addAttribute("student", student);
    return "update-student";
  }

  @PostMapping("/update/{id}")
  public String updateStudent(@PathVariable("id") long id, @Valid Student student,
                              BindingResult result, Model model) {
    if (result.hasErrors()) {
      student.setId(id);
      return "update-student";
    }
    studentRepository.save(student);
    model.addAttribute("students", studentRepository.findAll());
    return "index";
  }

  @GetMapping("/delete/{id}")
  public String deleteStudent(@PathVariable("id") long id, Model model) {
    Student student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    studentRepository.delete(student);
    model.addAttribute("students", studentRepository.findAll());
    return "index";
  }
}