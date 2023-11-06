package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.interfaces.ITheatreRepository;
import fontys.sem3.school.business.interfaces.TheatreService;
import fontys.sem3.school.domain.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreUseCase implements TheatreService {

    private final ITheatreRepository theatreRepository;

    public Theatre createTheatre (Theatre theatre){
        try{
            theatreRepository.createTheatre(theatre);
            return theatre;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Theatre not found");
        }

    }
    public boolean deleteTheatre (Theatre theatre){
        try{
            theatreRepository.deleteTheatre(theatre);
            return true;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Theatre not found");
        }
    }
    public Theatre updateTheatre (Long Id, String Name,String Details, String Country, String City,  int Popularity, int Capacity){
        try{
           Theatre theatre = theatreRepository.updateTheatre(Id, Name,Details, Country, City, Popularity,Capacity);
           return theatre;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Theatre not found");
        }
    }
    public List<Theatre> getTheatrebyFilter(Optional<String> Name, Optional<String> Country, Optional<String> City, Optional<Integer> SeatCapacity){
        try{
            List<Theatre> filteredTheatres = new ArrayList<>();
            filteredTheatres = theatreRepository.getTheatrebyFilter(Name, Country, City, SeatCapacity);
            return filteredTheatres;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Theatre not found");
        }
    }
    public Theatre getTheatrebyId(Long Id){
        try{
            Theatre theatre = theatreRepository.getTheatrebyId(Id);
            return theatre;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Theatre not found" + Id);
        }
    }



}
