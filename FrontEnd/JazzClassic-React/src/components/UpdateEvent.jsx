import React, { useState,useEffect } from 'react';
import EventService from '../services/EventService';
import TheatreService from '../services/TheatreService';
import './UpdateEvent.css'
const UpdateEvent = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [events, setEvents] = useState([]);
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [theatres, setTheatres] = useState([]);
  const [updatedEvent, setUpdatedEvent] = useState({
    name: '',
    date: '',
    time: '',
    zones: [],
    image: null,
  }); 
  useEffect(() => {
    TheatreService.findAll()
      .then(data => setTheatres(data))
      .catch(error => console.error(error));
   }, []);

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    EventService.filterEvent(e.target.value)
      .then(data => setEvents(data))
      .catch(error => console.error(error));
  };
  const handleZoneChange = (index, field) => (event) => {
    const newZones = [...updatedEvent.event.zones];
    newZones[index][field] = event.target.value;
    setUpdatedEvent((prevState) => ({
      ...prevState,
      event: {
        ...prevState.event,
        zones: newZones,
      },
    }));
   };
   const handleImageChange = (e) => {
    const images = Array.from(e.target.files);
    setUpdatedEvent((prevState) => ({
      ...prevState,
      event: {
        ...prevState.event,
        image: images,
      },
    }));
   };

  const handleRetrieveAll = () => {
    EventService.findAll()
      .then(data => setEvents(data))
      .catch(error => console.error(error));
      
  };

  const handleEventSelect = async (eventId) => {
    try {
      const event =   await EventService.getEvent(eventId);
      const formattedDate = event.event.date.substring(0, 10);

      const updatedEvent = {
        ...event,
        date: formattedDate,
        zones: event.zones || [],
      completed: event.completed,  // ensure zones is an array
        // add any other properties that might be missing in the fetched event
      };
      setSelectedEvent(updatedEvent);
      setUpdatedEvent(updatedEvent);
    } catch (error) {
      console.error('Error fetching event data:', error);
      // Handle error here
    }
  };

  const handleChange = (e, field) => {
    let value = e.target.value;

    // Handle completed separately
    if (field === 'completed') {
      value = e.target.checked ? 1 : 0;
    }
    setUpdatedEvent((prevState) => ({
      ...prevState,
      event: {
        ...prevState.event,
        [field]: value,
        
      },
    }));
   };

  const handleSubmit = (e) => {
    e.preventDefault();
    const eventData = {
      id: updatedEvent.event.id,
      name: updatedEvent.event.name,
      theatreId: Number(updatedEvent.event.theatreId),
      date: updatedEvent.event.date.substring(0, 10),
      time: updatedEvent.event.time,
      zones: updatedEvent.event.zones,
      image: updatedEvent.event.image,
      completed:updatedEvent.event.completed,
     };
    EventService.updateEvent(updatedEvent.event.id,eventData)
      .then(data => {
        alert('Event updated sucessfully.');
        // Handle successful update
      })
      .catch(error => {
        alert('Something went wrong', error);// Handle error during update
      });
  };
  const handleDelete = () => {
    if (!selectedEvent) {
      // Handle case where no event is selected
      return;
    }

    const eventData = {
      id: updatedEvent.event.id,
      name: updatedEvent.event.name,
      theatreId: Number(updatedEvent.event.theatreId),
      date: updatedEvent.event.date.substring(0, 10),
      time: updatedEvent.event.time,
      zones: updatedEvent.event.zones,
      image: updatedEvent.event.image,
      completed:updatedEvent.event.completed,
     };
    EventService.deleteEvent(updatedEvent.event.id,eventData)
      .then(data => {
        alert('Event updated sucessfully.');
        // Handle successful update
      })
      .catch(error => {
        alert('Something went wrong', error);// Handle error during update
      });
  };

  return (
    <div className="event-update-container">
      <input className="event-update-search-input" type="text" value={searchTerm} onChange={handleSearchChange} />
      <button className="event-update-retrieve-button" onClick={handleRetrieveAll}>Retrieve All Events</button>
  
      <ul className="event-update-event-list">
        {events.map(event => (
          <li className="event-update-event-list-item" key={event.id} onClick={() => handleEventSelect(event.id)}>
            {event.name}
          </li>
        ))}
      </ul>
  
      {selectedEvent && (
       
        <form className="event-update-form-container" onSubmit={handleSubmit}>
            <div className='title'><h2>Details Event</h2></div>

          <label className="event-update-form-label">
            Name:
            <input
              className="event-update-form-input"
              type="text"
              value={updatedEvent.event.name}
              onChange={(e) => handleChange(e, 'name')}
            />
          </label>
  
          <label className="event-update-form-label">
            Theatre:
            <select
              className="event-update-select-input"
              value={updatedEvent.event.theatreId}
              onChange={(e) => handleChange(e, 'theatreId')}
            >
              <option value="">Select Theatre</option>
              {theatres.map((theatre) => (
                <option key={theatre.id} value={theatre.id}>
                  {theatre.name}
                </option>
              ))}
            </select>
          </label>
  
          <label className="event-update-form-label">
            Date:
            <input
              className="event-update-form-input"
              type="date"
              value={updatedEvent.event.date.substring(0, 10)}
              onChange={(e) => handleChange(e, 'date')}
            />
          </label>
  
          <label className="event-update-form-label">
            Time:
            <input
              className="event-update-form-input"
              type="time"
              value={updatedEvent.event.time}
              onChange={(e) => handleChange(e, 'time')}
            />
          </label>
  
          <label className="event-update-form-label">
            Number of Zones:
            <select
              className="event-update-select-input"
              value={updatedEvent.event.zones ? updatedEvent.event.zones.length : 0}
              onChange={(e) => {
                const numZones = parseInt(e.target.value);
                const newZones = new Array(numZones).fill('').map(()=>({
                  price: '',
                  availableSeats: '',
                }));
                setUpdatedEvent((prevState) => ({
                  ...prevState,
                  event: {
                    ...prevState.event,
                    zones: newZones,
                  },
                }));
              }}
            >
              <option value="0">Select Number of Zones</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
            </select>
          </label>
  
          {updatedEvent.event.zones.map((zone, index) => (
            <div key={index} className="event-update-zone-container">
           
              <label className="event-update-form-label">
                Zone {index + 1} Price:
                <input
                  className="event-update-zone-input"
                  type="number"
                  value={zone.price}
                  onChange={handleZoneChange(index, 'price')}
                />
              </label>
              <label className="event-update-form-label">
                Zone {index + 1} Available Seats:
                <input
                  className="event-update-zone-input"
                  type="number"
                  value={zone.availableSeats}
                  onChange={handleZoneChange(index, 'availableSeats')}
                />
              </label>
            </div>
          ))}
  
          <label className="event-update-form-label">
            Image:
            <img
              className="event-update-image-preview"
              src={updatedEvent.event.image ? updatedEvent.event.image[0].url : (selectedEvent.event.image && selectedEvent.event.image[0].url)}
              alt="Event Image"
            />
            <input className="event-update-file-input" type="file" onChange={handleImageChange} />
          </label>
  
          <label className="event-update-form-label">
            Completed:
            <input
              className="event-update-checkbox-input"
              type="checkbox"
              value={updatedEvent.event.completed}
              checked={!!updatedEvent.event.completed}
              onChange={(e) => handleChange(e, 'completed')}
            />
          </label>
  
          <button className="event-update-submit-button" type="submit">Update Event</button>
          <button className="event-update-delete-button" type="button" onClick={handleDelete}>Delete Event</button>
        </form>
      )}
    </div>
  );
};

export default UpdateEvent;