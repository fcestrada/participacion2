package mx.unam.dgtic.servicio;

import mx.unam.dgtic.model.Alumno;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService {

    public List<Alumno> getAlumnosList();

    public Optional<Alumno> getAlumnoById(String matricula);

    Alumno updateAlumno(Alumno alumno);

    Alumno createAlumno(Alumno alumno);

    public boolean deleteAlumno(String matricula);

    public List<Alumno> findAlumnosByEstado(String estado);

}
