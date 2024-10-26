package mx.unam.dgtic.servicio;

import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService implements IAlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> getAlumnosList() {
        return alumnoRepository.findAll();
    }

    @Override
    public Optional<Alumno> getAlumnoById(String matricula) {
        return alumnoRepository.findById(matricula);
    }

    @Override
    public Alumno updateAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public boolean deleteAlumno(String matricula) {
        Optional<Alumno> alumno = alumnoRepository.findById(matricula);
        if (alumno.isPresent()) {
            alumnoRepository.delete(alumno.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Alumno> findAlumnosByEstado(String estado) {
        return alumnoRepository.findByEstadoEstado(estado);
    }

}
