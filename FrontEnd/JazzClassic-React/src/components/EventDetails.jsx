// EventDetails.js
import React, { useState, useEffect,useContext } from 'react';
import {  useNavigate ,useParams } from 'react-router-dom';
import EventService from '../services/EventService';
import TheatreService from '../services/TheatreService';
import PurchasePopUp from '../components/PurchasePopUp';
import UserContext from '../UserContext';
import './EventDetail.css';

function EventDetails() {
  const { eventId } = useParams();
  const [event, setEvent] = useState(null);
  const [theatre, setTheatre] = useState(null);
  const [showPurchaseModal, setShowPurchaseModal] = useState(false);
  const navigate = useNavigate(); 
  const { userId } = useContext(UserContext); 
  const totalAvailableSeats = event && event.event.zones.reduce((acc, zone) => acc + zone.availableSeats, 0);
  const ticketAvailabilityMessage = totalAvailableSeats > 0 ? 'Tickets Available' : 'No Tickets Available';
  const isPurchaseDisabled = totalAvailableSeats === 0;
  useEffect(() => {
    const fetchEvent = async () => {
      try {
        const eventData = await EventService.getEvent(eventId);
        setEvent(eventData);
        const theatreData = await TheatreService.getTheatre(eventData.event.theatreId);
       setTheatre(theatreData);
       console.log(userId);
      } catch (error) {
        console.error(`Error fetching event details: ${error}`);
      }
    };

    fetchEvent();
  }, [eventId]);

  if (!event,!theatre) return <div>Loading...</div>;
  const handlePurchaseClick = () => {
    // Show the PurchaseModal when the "Purchase Tickets" button is clicked
    setShowPurchaseModal(true);
  };
  const handlePurchaseModalClose = () => {
    // Close the PurchaseModal
    setShowPurchaseModal(false);
  };
  const handlePurchaseModalConfirm = (selectedTickets,userId,eventId,theatreId) => {
    navigate(`/purchase`, { state: { selectedTickets, userId, eventId, theatreId } });
    setShowPurchaseModal(false);
  };

    return (
        <div className="eventDetails">
        <div className="eventDetails-image">
            <img src={event.event.image[0]?.url} alt={event.event.name} />
        
            <div className="eventDetails-name">{event.event.name}</div>
        </div>
        <div className="eventDetails-image2"> <img src={event.event.image[0]?.url} alt={event.event.name} /></div>
        <div className="eventDetails-info">
        <p><span className="eventDetails-label">Date:</span> {event.event.date}</p>
        <p><span className="eventDetails-label">Time:</span> {event.event.time}</p>
        <p><span className="eventDetails-label">Location:</span> {theatre.country}, {theatre.city}, {theatre.name}</p>
        <p><span className="eventDetails-label">Description:</span> {ticketAvailabilityMessage}</p>
        </div>
        
        <button className="eventDetails-purchase-button" onClick={handlePurchaseClick} disabled={isPurchaseDisabled} >Purchase Tickets</button>
        {showPurchaseModal && (
        <PurchasePopUp
          zones={event.event.zones}
          onClose={handlePurchaseModalClose}
          onConfirm={(selectedTickets) => handlePurchaseModalConfirm(selectedTickets, userId, event.event.id,event.event.theatreId)}
        />
      )}
        </div>
    );
}

export default EventDetails;
