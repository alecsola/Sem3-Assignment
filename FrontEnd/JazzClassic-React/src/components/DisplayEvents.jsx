import React, { useEffect, useState } from 'react';
import EventService from '../services/EventService';
import './DisplayEvent.css'
import { useNavigate } from 'react-router-dom';

function EventList() {
  const [events, setEvents] = useState([]);
  const [searchName, setSearchName] = useState("");
  
  const navigate = useNavigate(); 
  useEffect(() => {
    getNotCompletedEvents();
  }, []);

  const getNotCompletedEvents = async () => {
    try {
      const response = await EventService.getByPopularity();
      setEvents(response);
    } catch (error) {
      console.error(`Error: ${error}`);
    }
  }; 
  const redirectToEventDetails = (eventId) => {
    navigate(`/event-details/${eventId}`);
  };

  const handleSearch = async () => {
    try {
      const response = await EventService.filterEvent(searchName);
      setEvents(response);
    } catch (error) {
      console.error(`Error: ${error}`);
    }
  };

  return (
    <div>
     <div className="search-bar-container">
      <input
        type="text"
        placeholder="Search by name"
        value={searchName}
        onChange={(e) => setSearchName(e.target.value)}
      />
      <button onClick={handleSearch}>Search</button>
      </div>
      <div className="display-event-container">
        {events.map((event) => {
          const eventDate = new Date(event.date);
          const formattedDate = eventDate.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
          });
          const eventTime = new Date('1970-01-01T' + event.time + 'Z');
            const formattedTime = eventTime.toLocaleTimeString('en-US', {
                hour: 'numeric',
                minute: '2-digit',
                hour12: true // use false for 24-hour format
            });

          return (
            <div className="display-event-item-container">
                <div key={event.id}>
                    <div className="display-event-image-container">
                    <img src={event.image[0]?.url} alt={event.name} />
                    </div>
                    <div className="display-event-body-container">
                    <div className="display-event-info">
                        <div className="overlay"></div>
                        <h2>{event.name}</h2>
                        <div className="separator"></div>
                        <p>{formattedDate}</p>
                
                    </div>
                    </div>
                    <div className="display-event-buy-button">
                        <button class="action" onClick={() => redirectToEventDetails(event.id)}>Buy Tickets</button>
                    </div>
                </div>
            </div>
          )
        })}
      </div>
    </div>
  );
}

export default EventList;