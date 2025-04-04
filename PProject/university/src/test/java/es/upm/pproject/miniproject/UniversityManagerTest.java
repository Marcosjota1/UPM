package es.upm.pproject.miniproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityManagerTest {
    private UniversityManager manager;
    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    public void setUp() {
        manager = new UniversityManagerImpl();

        // Configurar el capturador de logs
        Logger logger = (Logger) LoggerFactory.getLogger(UniversityManagerImpl.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        logger.setLevel(Level.INFO); // Asegurar que se capturen los logs de nivel INFO y superiores
    }

    @Test
    public void testRegisterCourseSuccess() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        assertEquals(1, manager.getAllCourses().size());
        assertEquals("Programming", manager.getAllCourses().get(0).getName());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertFalse(logs.isEmpty());
        assertEquals("Curso registrado exitosamente: code=101, name=Programming, coordinator=Dr. Smith",
                logs.get(0).getFormattedMessage());
    }

    @Test
    public void testRegisterCourseWithEmptyName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerCourse(101, "", "Dr. Smith");
        });
        assertEquals("El nombre del curso no puede estar vacío.", exception.getMessage());

        // Verificar el log de error
        List<ILoggingEvent> logs = listAppender.list;
        assertFalse(logs.isEmpty());
        assertEquals("Intento de registrar curso con nombre vacío: code=101", logs.get(0).getFormattedMessage());
        assertEquals(Level.ERROR, logs.get(0).getLevel());
    }

    @Test
    public void testRegisterCourseDuplicate() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerCourse(101, "Advanced Programming", "Dr. Jones");
        });
        assertEquals("El curso con código 101 ya está registrado.", exception.getMessage());

        // Verificar el log de error
        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(logs.size() >= 2);
        assertEquals("Intento de registrar curso ya existente: code=101", logs.get(1).getFormattedMessage());
    }

    @Test
    public void testRegisterStudentSuccess() {
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        assertEquals(1, manager.getAllStudents().size());
        assertEquals("John Doe", manager.getAllStudents().get(0).getName());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Estudiante registrado exitosamente: id=1, name=John Doe, email=john.doe@upm.es",
                logs.get(0).getFormattedMessage());
    }

    @Test
    public void testRegisterStudentWithInvalidEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerStudent(1, "John Doe", "invalid.email@");
        });
        assertEquals("El email del estudiante no tiene un formato válido.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Intento de registrar estudiante con email inválido: id=1, email=invalid.email@",
                logs.get(0).getFormattedMessage());
    }

    @Test
    public void testEnrollStudentSuccess() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.enrollStudent(1, 101);
        assertEquals(1, manager.getStudentsInCourse(101).size());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(logs.size() >= 3);
        assertEquals("Estudiante matriculado en curso: studentId=1, courseCode=101",
                logs.get(2).getFormattedMessage());
    }

    @Test
    public void testEnrollStudentCourseNotFound() {
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.enrollStudent(1, 101);
        });
        assertEquals("El curso con código 101 no está registrado.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Intento de matricular en curso no registrado: courseCode=101",
                logs.get(1).getFormattedMessage());
    }

    @Test
    public void testEnrollStudentLimitExceeded() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        for (int i = 1; i <= 50; i++) {
            manager.registerStudent(i, "Student " + i, "student" + i + "@upm.es");
            manager.enrollStudent(i, 101);
        }
        manager.registerStudent(51, "Extra Student", "extra@upm.es");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.enrollStudent(51, 101);
        });
        assertEquals("El curso ha alcanzado el límite de 50 estudiantes.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(logs.size() > 50);
        assertEquals("Límite de estudiantes alcanzado para el curso: courseCode=101",
                logs.get(logs.size() - 1).getFormattedMessage());
    }

    @Test
    public void testGetStudentsInCourseSuccess() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.registerStudent(2, "Jane Doe", "jane.doe@upm.es");
        manager.enrollStudent(1, 101);
        manager.enrollStudent(2, 101);
        List<Student> students = manager.getStudentsInCourse(101);
        assertEquals(2, students.size());
        assertEquals("John Doe", students.get(0).getName()); // Ordenado por ID

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Lista de estudiantes obtenida para el curso: courseCode=101, count=2",
                logs.get(logs.size() - 1).getFormattedMessage());
    }

    @Test
    public void testCancelEnrollmentSuccess() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.enrollStudent(1, 101);
        manager.cancelEnrollment(1, 101);
        assertEquals(0, manager.getStudentsInCourse(101).size());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Matrícula cancelada: studentId=1, courseCode=101",
                logs.get(3).getFormattedMessage());
    }

    @Test
    public void testCancelEnrollmentNotEnrolled() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.cancelEnrollment(1, 101);
        });
        assertEquals("El estudiante no está matriculado en este curso.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Estudiante no está matriculado en el curso: studentId=1, courseCode=101",
                logs.get(2).getFormattedMessage());
    }

    @Test
    public void testRestartCourseSuccess() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.enrollStudent(1, 101);
        manager.restartCourse(101);
        assertEquals(0, manager.getStudentsInCourse(101).size());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Curso reiniciado: courseCode=101",
                logs.get(3).getFormattedMessage());
    }

    @Test
    public void testGetAllStudentsSuccess() {
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.registerStudent(2, "Jane Doe", "jane.doe@upm.es");
        List<Student> students = manager.getAllStudents();
        assertEquals(2, students.size());
        assertEquals("John Doe", students.get(0).getName()); // Ordenado por ID

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Lista de todos los estudiantes obtenida: count=2",
                logs.get(2).getFormattedMessage());
    }

    @Test
    public void testGetAllCoursesSuccess() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerCourse(102, "Math", "Dr. Jones");
        List<Course> courses = manager.getAllCourses();
        assertEquals(2, courses.size());
        assertEquals("Programming", courses.get(0).getName()); // Ordenado por código

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertEquals("Lista de todos los cursos obtenida: count=2",
                logs.get(2).getFormattedMessage());
    }

    @Test
    public void testRegisterCourseWithWhitespaceName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerCourse(101, "   ", "Dr. Smith");
        });
        assertEquals("El nombre del curso no puede estar vacío.", exception.getMessage());
    }

    @Test
    public void testRegisterCourseWithEmptyCoordinator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerCourse(101, "Programming", "");
        });
        assertEquals("El coordinador del curso no puede estar vacío.", exception.getMessage());
    }

    @Test
    public void testRegisterStudentWithEmailEndingInDot() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerStudent(1, "John Doe", "john.doe@upm.");
        });
        assertEquals("El email del estudiante no tiene un formato válido.", exception.getMessage());
    }

    @Test
    public void testRegisterStudentWithWhitespaceName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerStudent(1, "   ", "john.doe@upm.es");
        });
        assertEquals("El nombre del estudiante no puede estar vacío.", exception.getMessage());
    }

    @Test
    public void testEnrollStudentAlreadyEnrolled() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.enrollStudent(1, 101);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.enrollStudent(1, 101);
        });
        assertEquals("El estudiante ya está matriculado en este curso.", exception.getMessage());
    }

    @Test
    public void testEnrollStudentAtLimit() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        for (int i = 1; i <= 50; i++) {
            manager.registerStudent(i, "Student " + i, "student" + i + "@upm.es");
            manager.enrollStudent(i, 101);
        }
        manager.registerStudent(51, "Extra Student", "extra@upm.es");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.enrollStudent(51, 101);
        });
        assertEquals("El curso ha alcanzado el límite de 50 estudiantes.", exception.getMessage());
    }

    @Test
    public void testCancelEnrollmentCourseNotFound() {
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.cancelEnrollment(1, 101);
        });
        assertEquals("El curso con código 101 no está registrado.", exception.getMessage());
    }

    @Test
    public void testCancelEnrollmentStudentNotFound() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.cancelEnrollment(999, 101);
        });
        assertEquals("El estudiante con ID 999 no está registrado.", exception.getMessage());
    }

    @Test
    public void testGetStudentsInCourseEmpty() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        List<Student> students = manager.getStudentsInCourse(101);
        assertTrue(students.isEmpty());
    }

    @Test
    public void testGetStudentsInCourseNotFound() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.getStudentsInCourse(999);
        });
        assertEquals("El curso con código 999 no está registrado.", exception.getMessage());
    }

    @Test
    public void testRestartCourseEmpty() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.restartCourse(101);
        assertTrue(manager.getStudentsInCourse(101).isEmpty());
    }

    @Test
    public void testRestartCourseWithStudents() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        manager.enrollStudent(1, 101);
        manager.restartCourse(101);
        assertTrue(manager.getStudentsInCourse(101).isEmpty());
    }

    @Test
    public void testGetAllStudentsEmpty() {
        assertTrue(manager.getAllStudents().isEmpty());
    }

    @Test
    public void testGetAllCoursesEmpty() {
        assertTrue(manager.getAllCourses().isEmpty());
    }

    @Test
    public void testGetAllStudentsOrdered() {
        manager.registerStudent(2, "Jane Doe", "jane.doe@upm.es");
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        List<Student> students = manager.getAllStudents();
        assertEquals(1, students.get(0).getId());
        assertEquals(2, students.get(1).getId());
    }

    @Test
    public void testRegisterStudentWithNullEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerStudent(1, "John Doe", null);
        });
        assertEquals("El email del estudiante no puede estar vacío.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertFalse(logs.isEmpty());
        assertEquals("Intento de registrar estudiante con email vacío: id=1", logs.get(0).getFormattedMessage());
    }

    @Test
    public void testRegisterStudentWithEmptyEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerStudent(1, "John Doe", "");
        });
        assertEquals("El email del estudiante no puede estar vacío.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertFalse(logs.isEmpty());
        assertEquals("Intento de registrar estudiante con email vacío: id=1", logs.get(0).getFormattedMessage());
    }

    @Test
    public void testRegisterStudentDuplicate() {
        manager.registerStudent(1, "John Doe", "john.doe@upm.es");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.registerStudent(1, "Jane Doe", "jane.doe@upm.es");
        });
        assertEquals("El estudiante con ID 1 ya está registrado.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(logs.size() >= 2);
        assertEquals("Intento de registrar estudiante ya existente: id=1", logs.get(1).getFormattedMessage());
    }

    @Test
    public void testEnrollStudentNotRegistered() {
        manager.registerCourse(101, "Programming", "Dr. Smith");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.enrollStudent(999, 101);
        });
        assertEquals("El estudiante con ID 999 no está registrado.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertTrue(logs.size() >= 1);
        assertEquals("Intento de matricular estudiante no registrado: studentId=999", logs.get(1).getFormattedMessage());
    }

    @Test
    public void testRestartCourseNotRegistered() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            manager.restartCourse(999);
        });
        assertEquals("El curso con código 999 no está registrado.", exception.getMessage());

        // Verificar el log
        List<ILoggingEvent> logs = listAppender.list;
        assertFalse(logs.isEmpty());
        assertEquals("Intento de reiniciar curso no registrado: courseCode=999", logs.get(0).getFormattedMessage());
    }
}
