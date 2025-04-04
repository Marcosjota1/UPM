package es.pruebaMarcos.demo.test;

import org.springframework.web.bind.annotation.*;
import es.pruebaMarcos.demo.Alumno;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/alumnos")
public class AlumnoController {

    private List<Alumno> listaAlumnos = new ArrayList<>(); // Aquí accederás a BBDD

    @PostMapping("/crear")
    public String crearAlumno(@RequestBody Alumno alumno) {
        listaAlumnos.add(alumno);
        return "Alumno creado exitosamente";
    }

    @GetMapping("/{usuario}")
    public Alumno obtenerAlumno(@PathVariable String usuario) {
        return listaAlumnos.stream()
                .filter(a -> a.getUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/todos")
    public List<Alumno> obtenerTodos() {
        return listaAlumnos;
    }

    // Actualizar un alumno por usuario
    @PutMapping("/actualizar/{usuario}")
    public String actualizarAlumno(@PathVariable String usuario, @RequestBody Alumno alumnoActualizado) {
        Optional<Alumno> alumnoOpt = listaAlumnos.stream()
                .filter(a -> a.getUsuario().equals(usuario))
                .findFirst();

        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            alumno.setCorreo(alumnoActualizado.getCorreo());
            alumno.setMatricula(alumnoActualizado.getMatricula());
            alumno.setFechaNacimiento(alumnoActualizado.getFechaNacimiento());
            return "Alumno actualizado correctamente";
        } else {
            return "Alumno no encontrado";
        }
    }

    // Borrar un alumno por usuario
    @DeleteMapping("/borrar/{usuario}")
    public String borrarAlumno(@PathVariable String usuario) {
        boolean eliminado = listaAlumnos.removeIf(alumno -> alumno.getUsuario().equals(usuario));
        return eliminado ? "Alumno eliminado exitosamente" : "Alumno no encontrado";
    }
}
