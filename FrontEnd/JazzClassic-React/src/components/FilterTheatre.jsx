import React, { useEffect, useState } from 'react';
import TheatreService from '../services/TheatreService';

const FilterComponent = ({ name, country, city, shouldFetch }) => {
  const [theatres, setTheatres] = useState([]);

  useEffect(() => {
    async function fetchTheatres() {
      try {
        const data =  await TheatreService.filterTheatre(name, country, city);
        console.log('Fetched theatres:', data);
         setTheatres(data);
      } catch (error) {
        console.error('Error fetching theatres:', error);
      }
    }

        if (shouldFetch) {
      fetchTheatres();
    }
  }, [shouldFetch]);

  // Render your theatres data here
  return (
    <div>
      {theatres.map((theatre, index) => (
        <div className="theatre-card" key={index}>
          <h2 className="theatre-name">{theatre.name}</h2>
          <div className="theatre-image">
            {theatre.image.map((image, imageIndex) => (
              <img key={imageIndex} src={image.url} alt={`image ${imageIndex}`} />
            ))}
          </div>
          <div className="theatre-location">
            <p className="theatre-country">{theatre.country}</p>
            <p className="theatre-city">{theatre.city}</p>
          </div>
          <button className="buy-tickets" onClick={() => handleBuyClick(theatre.Id)}>
            Buy Tickets
          </button>
        </div>
      ))}
    </div>
  );
    
};

export default FilterComponent;