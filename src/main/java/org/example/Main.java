package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    static List<Employee> employeeList = new ArrayList<>();
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();
        mapYearEmployee(employeeList);
    }

    private static void printAllDistinctProject(List<Employee> employees) {
        employees.stream().flatMap(employee -> employee.getProjects().stream()).map(project -> project.getName()).distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    private static void printEmployeeNameStartWithA(List<Employee> employees) {
        employees.stream().filter(employee -> employee.getFirstName().startsWith("A")).forEach(employee -> System.out.println(employee.getLastName() + " " + employee.getFirstName()));
    }

    private static void printEmployeeJoinedIn2023(List<Employee> employees) {
        employees.stream().filter(employee -> employee.getId().startsWith("2023")).forEach(System.out::println);
    }

    private static void printEmployeeSortedByFirstNameSalary(List<Employee> employees) {
        Comparator<Employee> compareByFirstName = Comparator.comparing(Employee::getFirstName);
        Comparator<Employee> compareBySalary = Comparator.comparing(Employee::getSalary);
        Comparator<Employee> comparator = compareByFirstName.thenComparing(compareBySalary);
        employees.stream().sorted(comparator).forEach(System.out::println);
    }

    private static void printMinSalary(List<Employee> employees) {
        System.out.println(employees.stream().mapToInt(Employee::getSalary).min().orElse(0));
    }

    private static void printAllEmployeeWithMinSalary(List<Employee> employees) {
        int minSalary = employees.stream().mapToInt(Employee::getSalary).min().orElse(0);
        employees.stream().filter(employee -> employee.getSalary() == minSalary).forEach(System.out::println);
    }

    private static void printEmployeesWithMoreThan2Projects(List<Employee> employees) {
        employees.stream().filter(employee -> employee.getProjects().size() > 1).forEach(System.out::println);
    }

    private static void countTotalLaptops(List<Employee> employees) {
        System.out.println(employees.stream().mapToInt(Employee::getTotalLaptopsAssigned).sum());
    }

    private static void countAllProjectWithRobertDowneyJr(List<Employee> employees) {
        System.out.println(employees.stream().flatMap(employee -> employee.getProjects().stream()).filter(project -> "Robert Downey Jr".equals(project.getProjectManager())).distinct().count());
    }

    private static void listOfAllProjectWithRobertDowneyJr(List<Employee> employees) {
        List<Project> projectsWithRobert = employees.stream().flatMap(employee -> employee.getProjects().stream()).filter(project -> "Robert Downey Jr".equals(project.getProjectManager())).distinct().toList();
        System.out.println(projectsWithRobert);
    }

    private static void mapYearEmployee(List<Employee> employees) {
        Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(s ->  s.getId().substring(0,4), TreeMap::new,Collectors.toList()));
        System.out.println(map);
    }

    private static void mapYearCount(List<Employee> employees) {
        Map<String, Long> map = employees.stream().collect(Collectors.groupingBy(s ->  s.getId().substring(0,4),Collectors.counting()));
        System.out.println(map);
    }

}