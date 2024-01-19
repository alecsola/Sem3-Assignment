import React, { useState } from 'react';
import TheatreService from '../services/TheatreService'; 
import './CreateTheatre.css';
function TheatreForm() {
  const [theatreData, setTheatreData] = useState({
    name: '',
    image: [],
    country: '',
    city: '',
    details:'',
    capacity:'',
    popularity:'',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    const parsedValue = name === 'capacity' ? parseInt(value, 10) : value;
    setTheatreData({ ...theatreData, [name]: parsedValue });
  };
  const handleFileChange = (e) => {
    const files = Array.from(e.target.files);
    setTheatreData({ ...theatreData, image: files });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await TheatreService.createTheatre(theatreData);
      alert('Theatre Created Succsesfully');
      // Optionally, you can redirect or handle success in another way
    } catch (error) {
      console.error('Error creating theatre:', error);
      alert('An error occurred while creating the theatre.');
      // Handle error appropriately
    }
  };

  return (
    
    <div className='create-theatre-container'>
        <h2>Create Theatre</h2>
        <form onSubmit={handleSubmit}>
        <div className='form-group-left'>
            <div className='input-group'>
            <label>Name:</label>
            <input type="text" name="name" value={theatreData.name} onChange={handleInputChange} />
            </div>
            <div className='input-group'>
            <label>Country:</label>
            <input type="text" name="country" value={theatreData.country} onChange={handleInputChange} />
            </div>
            <div className='input-group'>
            <label>City:</label>
            <input type="text" name="city" value={theatreData.city} onChange={handleInputChange} />
            </div>
        </div>
        <div className='form-group-right'>
            <div className='input-group'>
            <label>Details:</label>
            <textarea
                name="details"
                value={theatreData.details}
                onChange={handleInputChange}
                rows="4"
                placeholder="Enter details..."
            />
            </div>
            <div className='input-group'>
            <label>Capacity:</label>
            <input type="number" name="capacity" value={theatreData.capacity} onChange={handleInputChange} />
            </div>
            <div className='input-group'>
            <label>Popularity (0-100):</label>
            <input
                type="number"
                name="popularity"
                value={theatreData.popularity}
                onChange={(e) => {
                const newValue = Math.min(100, Math.max(0, e.target.value));
                setTheatreData({ ...theatreData, popularity: newValue });
                }}
            />
            </div>
            <div className='input-group'>
            <label>Images:</label>
            <input type="file" name="image" multiple onChange={handleFileChange} />
            </div>
        </div>
        {/* Add other form fields */}
        <button className='buttonCreateTheatre' type="submit">Create Theatre</button>
        </form>
    </div>
  );
}

export default TheatreForm;