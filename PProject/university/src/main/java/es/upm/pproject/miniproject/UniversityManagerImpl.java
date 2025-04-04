package es.upm.pproject.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UniversityManagerImpl implements UniversityManager {
    private static final Logger logger = LoggerFactory.getLogger(UniversityManagerImpl.class);

    // Mapa para almacenar cursos (clave: código del curso, valor: objeto Course)
    private final Map<Integer, Course> courses = new HashMap<>();
    // Mapa para almacenar estudiantes (clave: ID del estudiante, valor: objeto Student)
    private final Map<Integer, Student> students = new HashMap<>();
    // Mapa para almacenar matrículas (clave: código del curso, valor: lista de IDs de estudiantes)
    private final Map<Integer, List<Integer>> enrollments = new HashMap<>();

    // Máximo número de estudiantes por curso
    private static final int MAX_STUDENTS_PER_COURSE = 50;

    // Patrón para validar email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Constantes para evitar duplicación de literales
    private static final String COURSE_PREFIX = "El curso con código ";
    private static final String STUDENT_PREFIX = "El estudiante con ID ";
    private static final String NOT_REGISTERED_SUFFIX = " no está registrado.";

    @Override
    public void registerCourse(int code, String name, String coordinator) throws IllegalArgumentException {
        // Validaciones
        if (name == null || name.trim().isEmpty()) {
            logger.error("Intento de registrar curso con nombre vacío: code={}", code);
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío.");
        }
        if (coordinator == null || coordinator.trim().isEmpty()) {
            logger.error("Intento de registrar curso con coordinador vacío: code={}", code);
            throw new IllegalArgumentException("El coordinador del curso no puede estar vacío.");
        }
        if (courses.containsKey(code)) {
            logger.error("Intento de registrar curso ya existente: code={}", code);
            throw new IllegalArgumentException(COURSE_PREFIX + code + " ya está registrado.");
        }

        // Registro del curso
        Course course = new Course(code, name.trim(), coordinator.trim());
        courses.put(code, course);
        logger.info("Curso registrado exitosamente: code={}, name={}, coordinator={}", code, name, coordinator);
    }

    @Override
    public void registerStudent(int id, String name, String email) throws IllegalArgumentException {
        // Validaciones
        if (name == null || name.trim().isEmpty()) {
            logger.error("Intento de registrar estudiante con nombre vacío: id={}", id);
            throw new IllegalArgumentException("El nombre del estudiante no puede estar vacío.");
        }
        if (email == null || email.trim().isEmpty()) {
            logger.error("Intento de registrar estudiante con email vacío: id={}", id);
            throw new IllegalArgumentException("El email del estudiante no puede estar vacío.");
        }
        if (!EMAIL_PATTERN.matcher(email).matches() || email.endsWith(".")) {
            logger.error("Intento de registrar estudiante con email inválido: id={}, email={}", id, email);
            throw new IllegalArgumentException("El email del estudiante no tiene un formato válido.");
        }
        if (students.containsKey(id)) {
            logger.error("Intento de registrar estudiante ya existente: id={}", id);
            throw new IllegalArgumentException(STUDENT_PREFIX + id + " ya está registrado.");
        }

        // Registro del estudiante
        Student student = new Student(id, name.trim(), email.trim());
        students.put(id, student);
        logger.info("Estudiante registrado exitosamente: id={}, name={}, email={}", id, name, email);
    }

    @Override
    public void enrollStudent(int studentId, int courseCode) throws IllegalStateException {
        // Validaciones
        if (!students.containsKey(studentId)) {
            logger.error("Intento de matricular estudiante no registrado: studentId={}", studentId);
            throw new IllegalStateException(STUDENT_PREFIX + studentId + NOT_REGISTERED_SUFFIX);
        }
        if (!courses.containsKey(courseCode)) {
            logger.error("Intento de matricular en curso no registrado: courseCode={}", courseCode);
            throw new IllegalStateException(COURSE_PREFIX + courseCode + NOT_REGISTERED_SUFFIX);
        }

        List<Integer> enrolledStudents = enrollments.computeIfAbsent(courseCode, k -> new ArrayList<>());
        if (enrolledStudents.contains(studentId)) {
            logger.error("Estudiante ya matriculado en el curso: studentId={}, courseCode={}", studentId, courseCode);
            throw new IllegalStateException("El estudiante ya está matriculado en este curso.");
        }
        if (enrolledStudents.size() >= MAX_STUDENTS_PER_COURSE) {
            logger.error("Límite de estudiantes alcanzado para el curso: courseCode={}", courseCode);
            throw new IllegalStateException("El curso ha alcanzado el límite de " + MAX_STUDENTS_PER_COURSE + " estudiantes.");
        }

        // Matriculación
        enrolledStudents.add(studentId);
        logger.info("Estudiante matriculado en curso: studentId={}, courseCode={}", studentId, courseCode);
    }

    @Override
    public List<Student> getStudentsInCourse(int courseCode) {
        // Validación
        if (!courses.containsKey(courseCode)) {
            logger.error("Intento de listar estudiantes de curso no registrado: courseCode={}", courseCode);
            throw new IllegalStateException(COURSE_PREFIX + courseCode + NOT_REGISTERED_SUFFIX);
        }

        // Obtener lista de estudiantes matriculados de forma optimizada
        List<Integer> enrolledStudentIds = enrollments.getOrDefault(courseCode, Collections.emptyList());
        List<Student> result = enrolledStudentIds.stream()
                .map(students::get)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Student::getId))
                .collect(Collectors.toList());

        logger.info("Lista de estudiantes obtenida para el curso: courseCode={}, count={}", courseCode, result.size());
        return result;
    }

    @Override
    public void cancelEnrollment(int studentId, int courseCode) throws IllegalStateException {
        // Validaciones
        if (!students.containsKey(studentId)) {
            logger.error("Intento de cancelar matrícula de estudiante no registrado: studentId={}", studentId);
            throw new IllegalStateException(STUDENT_PREFIX + studentId + NOT_REGISTERED_SUFFIX);
        }
        if (!courses.containsKey(courseCode)) {
            logger.error("Intento de cancelar matrícula en curso no registrado: courseCode={}", courseCode);
            throw new IllegalStateException(COURSE_PREFIX + courseCode + NOT_REGISTERED_SUFFIX);
        }

        List<Integer> enrolledStudents = enrollments.get(courseCode);
        if (enrolledStudents == null || !enrolledStudents.contains(studentId)) {
            logger.error("Estudiante no está matriculado en el curso: studentId={}, courseCode={}", studentId, courseCode);
            throw new IllegalStateException("El estudiante no está matriculado en este curso.");
        }

        // Cancelar matrícula
        enrolledStudents.remove(Integer.valueOf(studentId));
        logger.info("Matrícula cancelada: studentId={}, courseCode={}", studentId, courseCode);
    }

    @Override
    public void restartCourse(int courseCode) {
        // Validación
        if (!courses.containsKey(courseCode)) {
            logger.error("Intento de reiniciar curso no registrado: courseCode={}", courseCode);
            throw new IllegalStateException(COURSE_PREFIX + courseCode + NOT_REGISTERED_SUFFIX);
        }

        // Reiniciar curso (eliminar todas las matrículas)
        enrollments.remove(courseCode);
        logger.info("Curso reiniciado: courseCode={}", courseCode);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>(students.values());
        // Ordenar por ID
        result.sort(Comparator.comparingInt(Student::getId));
        logger.info("Lista de todos los estudiantes obtenida: count={}", result.size());
        return result;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> result = new ArrayList<>(courses.values());
        // Ordenar por código
        result.sort(Comparator.comparingInt(Course::getCode));
        logger.info("Lista de todos los cursos obtenida: count={}", result.size());
        return result;
    }
}
