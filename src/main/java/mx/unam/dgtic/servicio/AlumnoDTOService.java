package mx.unam.dgtic.servicio;

import mx.unam.dgtic.dto.AlumnoDTO;
import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.model.Estado;
import mx.unam.dgtic.repository.AlumnoRepository;
import mx.unam.dgtic.repository.EstadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoDTOService implements IAlumnoDTOService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AlumnoDTO> getAlumnosList() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlumnoDTO> getAlumnosPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Alumno> pageResult = alumnoRepository.findAll(pageRequest);
        //return pageResult.stream().toList();
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<AlumnoDTO> getAlumnoById(String matricula) {
        Optional<Alumno> alumno = alumnoRepository.findById(matricula);
        if (alumno.isPresent()) {
            AlumnoDTO alumnoDTO = convertToDTO(alumno.get());
            return Optional.of(alumnoDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public AlumnoDTO updateAlumno(AlumnoDTO alumno) throws ParseException {
        return convertToDTO(alumnoRepository.save(this.convertToEntity(alumno)));
    }

    @Override
    public AlumnoDTO createAlumno(AlumnoDTO alumno) throws ParseException {
        return convertToDTO(alumnoRepository.save(this.convertToEntity(alumno)));
    }

    @Override
    public boolean deleteAlumno(String matricula) {
        Optional<Alumno> alumno = alumnoRepository.findById(matricula);
        if (alumno.isPresent()) {
            alumnoRepository.deleteById(matricula);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<AlumnoDTO> findAlumnosByEstado(String estado) {
        List<Alumno> alumnos = alumnoRepository.findByEstadoEstado(estado);
        return alumnos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AlumnoDTO convertToDTO(Alumno alumno) {
        AlumnoDTO alumnoDTO = modelMapper.map(alumno, AlumnoDTO.class);
        System.out.println("Alumno2AlumnoDTO: " + alumnoDTO.toString());
        if (alumno.getEstado() != null) {
            alumnoDTO.setEstado(alumno.getEstado().getEstado());
        }
        if (alumno.getFnac() != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            alumnoDTO.setFnac(dateFormat.format(alumno.getFnac()));
        }
        return alumnoDTO;
    }

    private Alumno convertToEntity(AlumnoDTO alumnoDTO) throws ParseException {
        Alumno alumno = modelMapper.map(alumnoDTO, Alumno.class);
        System.out.println("AlumnoDTO2Alumno: " + alumno.toString());
        if (alumnoDTO.getEstado() != null && !alumnoDTO.getEstado().isBlank()) {
            Estado estado = estadoRepository.findByEstado(alumnoDTO.getEstado());
            alumno.setEstado(estado);
        }
        if (alumnoDTO.getFnac() != null && !alumnoDTO.getFnac().isBlank()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            alumno.setFnac(dateFormat.parse(alumnoDTO.getFnac()));
        } else {
            alumno.setFnac(new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01"));
        }
        return alumno;
    }

}
