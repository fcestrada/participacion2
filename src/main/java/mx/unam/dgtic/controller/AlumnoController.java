package mx.unam.dgtic.controller;

import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.servicio.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/alumnos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AlumnoController {

    @Autowired
    private IAlumnoService alumnoService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Alumno>> getAll() {
        return ResponseEntity.ok(alumnoService.getAlumnosList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable("id") String id) {
        Optional<Alumno> alumno = alumnoService.getAlumnoById(id);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteAlumno(@PathVariable("id") String id) {
        if (alumnoService.deleteAlumno(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) throws URISyntaxException {
        Alumno alumnoNuevo = alumnoService.createAlumno(alumno);
        URI location = new URI("/api/alumnos/" + alumnoNuevo.getMatricula());
        return ResponseEntity.created(location).body(alumnoNuevo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Alumno> updateFullAlumno(@RequestBody Alumno alumno, @PathVariable("id") String id) throws URISyntaxException {
        Optional<Alumno> alumnoDB = alumnoService.getAlumnoById(id);
        if (alumnoDB.isPresent()) {
            return ResponseEntity.ok(alumnoService.updateAlumno(alumno));
        } else {
            Alumno alumnoNuevo = alumnoService.createAlumno(alumno);
            URI location = new URI("/api/alumnos/" + alumnoNuevo.getMatricula());
            return ResponseEntity.created(location).body(alumnoNuevo);
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Alumno> updatePartialAlumno(@RequestBody Alumno alumno, @PathVariable("id") String id) throws URISyntaxException {
        Optional<Alumno> alumnoDB = alumnoService.getAlumnoById(id);
        if (alumnoDB.isPresent()) {
            Alumno alumnoToUpdate = alumnoDB.get();
            if (alumno.getNombre() != null) {
                alumnoToUpdate.setNombre(alumno.getNombre());
            }
            if (alumno.getPaterno() != null) {
                alumnoToUpdate.setPaterno(alumno.getPaterno());
            }
            if (alumno.getFnac() != null) {
                alumnoToUpdate.setFnac(alumno.getFnac());
            }
            if (alumno.getEstatura() != 0.0) {
                alumnoToUpdate.setEstatura(alumno.getEstatura());
            }
            return ResponseEntity.ok(alumnoService.updateAlumno(alumnoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/estados/{edo}")
    public ResponseEntity<List<Alumno>> getByEstado(@PathVariable String edo) {
        return ResponseEntity.ok(alumnoService.findAlumnosByEstado(edo));
    }

}