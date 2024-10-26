package mx.unam.dgtic.servicio;

import mx.unam.dgtic.dto.AlumnoDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAlumnoDTOService {

    public List<AlumnoDTO> getAlumnosList();

    public List<AlumnoDTO> getAlumnosPageable(int page, int size, String dirSort, String sort);

    public Optional<AlumnoDTO> getAlumnoById(String matricula);

    AlumnoDTO updateAlumno(AlumnoDTO alumno) throws ParseException;

    AlumnoDTO createAlumno(AlumnoDTO alumno) throws ParseException;

    public boolean deleteAlumno(String matricula);

    public List<AlumnoDTO> findAlumnosByEstado(String estado);

}