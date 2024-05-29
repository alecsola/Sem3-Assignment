package fontys.sem3.school.persistence.repository;


import fontys.sem3.school.business.interfaces.ITheatreRepositoryBusiness;
import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.ITheatreRepository;
import fontys.sem3.school.persistence.JPAmappers.EventJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;
import fontys.sem3.school.persistence.converters.EventConverter;
import fontys.sem3.school.persistence.converters.TheatreConverter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
@RequiredArgsConstructor
public class TheatreRepository implements ITheatreRepositoryBusiness {

    private final List<Theatre> theatres;
    private final ITheatreRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public long createTheatre(Theatre theatre){
        TheatreJPAmapper theatreJPAmapper = TheatreConverter.convertJPAtheatre(theatre);
        theatreJPAmapper.getImage().forEach(image -> image.setTheatre(theatreJPAmapper));
        return repository.save(theatreJPAmapper).getId();
    }
    @Transactional
    public List<Theatre> filterTheatres(String name, String city, String country) {
        List<TheatreJPAmapper> theatreJPAmappers = repository.findTheatresByFilter(name,city,country);
        return TheatreConverter.convertTheatre(theatreJPAmappers);
    }
    @Transactional
    public List<Theatre> findAll(){
        List<TheatreJPAmapper> theatreJPAmappers = repository.findAll();
        return TheatreConverter.convertTheatre(theatreJPAmappers);
    }
    @Transactional
    public Theatre getTheatrebyId(long id){
        List<TheatreJPAmapper> theatreJPAmappers = repository.findAll();
        for(TheatreJPAmapper theatreJPAmapper:theatreJPAmappers){
            if(theatreJPAmapper.getId().equals(id)){
                return TheatreConverter.convertTheatreObject(theatreJPAmapper);
            }
        }return null;
    }








}
