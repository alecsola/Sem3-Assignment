import React, { useState, useEffect } from 'react';
    import EventService from '../services/EventService';
    import TheatreService from '../services/TheatreService';
    import './CreateEvent.css'
    const CreateEvent = () => {
    const [name, setName] = useState('');
    const [theatreId, setTheatreId] = useState('');
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [zones, setZones] = useState([]);
    const [image, setImage] = useState(null);
    const [theatres, setTheatres] = useState([]);
      

    
    const handleZoneChange = (index, field) => event => {
        const newZones = [...zones];
        newZones[index][field] = event.target.value;
        setZones(newZones);
    };
    useEffect(() => {
        // Fetch theatres when component mounts
        async function fetchTheatres() {
          try {
            const theatresData = await TheatreService.findAll();
            setTheatres(theatresData);
          } catch (error) {
            console.error('Error fetching theatres:', error);
          }
        }
    
        fetchTheatres();
      }, []); 
    
    
    const handleImageChange = (e) => {
        const image = Array.from(e.target.files);
        setImage(image);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const eventData = {
        name,
        theatreId: Number(theatreId),
        date,
        time,
        zones ,
        image,
        };

        try {
        await EventService.createEvent(eventData);
        alert('Event created successfully!');
        // Handle successful creation
        } catch (error) {
          alert('An error occurred while creating the event.');
        // Handle error during creation
        }
    };

    return (
      
        <div className="form-container">
         <form className="create-event-form" onSubmit={handleSubmit}>
            <h2 className="create-event-page-title">Create Event</h2>
           <label>
             Name:
             <input type="text" value={name} onChange={e => setName(e.target.value)} />
           </label>
           <label>
             Theatre:
             <select value={theatreId} onChange={(e) => setTheatreId(e.target.value)}>
               <option value="">Select Theatre</option>
               {theatres.map((theatre) => (
                 <option key={theatre.id} value={theatre.id}>
                   {theatre.name}
                 </option>
               ))}
             </select>
           </label>
           <label>
             Date:
             <input type="date" value={date} onChange={e => setDate(e.target.value)} />
           </label>
           <label>
             Time:
             <input type="time" value={time} onChange={e => setTime(e.target.value)} />
           </label>
           <label>
             Number of Zones:
             <select value={zones.length} onChange={e => setZones(
        new Array(parseInt(e.target.value)).fill('').map(() => ({price: '',availableSeats: '',})))}>
               {[...Array(5).keys()].map(i => (
                 <option key={i} value={i}>{i}</option>
               ))}
             </select>
           </label>
           {zones.map((zone, index) => (
             <div key={index} className="create-event-zone-container">
               <label>
                 Zone {index + 1} Price:
                 <input type="number" value={zone.price} onChange={handleZoneChange(index, 'price')} />
               </label>
               <label>
                 Zone {index + 1} Available Seats:
                 <input type="number" value={zone.availableSeats} onChange={handleZoneChange(index, 'availableSeats')} />
               </label>
             </div>
           ))}
           <label>
             Image:
             <input type="file" onChange={handleImageChange} />
           </label>
           <button type="submit" className="create-event-form-submit-button">Create Event</button>
         </form>
         </div>
      
       );
    };

    export default CreateEvent;