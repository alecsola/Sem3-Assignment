import React, { useState } from 'react';
import './HomePage.css'
import FilterComponent from '../components/FilterTheatre';

const HomePage = () => {
  const [name, setName] = useState('');
  const [country, setCountry] = useState('');
  const [city, setCity] = useState('');
  const [shouldFetch, setShouldFetch] = useState(false);

  const handleSearch = () => {
    setShouldFetch(true);
   
  };
  return (
    <div className="input-container">
      <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Name" />
      <input type="text" value={country} onChange={(e) => setCountry(e.target.value)} placeholder="Country" />
      <input type="text" value={city} onChange={(e) => setCity(e.target.value)} placeholder="City" />

      <button onClick={handleSearch}>Search</button>
      <FilterComponent name={name} country={country} city={city} shouldFetch={shouldFetch} />
    </div>
  );
};

export default HomePage;