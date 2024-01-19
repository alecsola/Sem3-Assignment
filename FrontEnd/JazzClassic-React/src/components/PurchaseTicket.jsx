// Purchase.js
import React, { useState,useEffect } from 'react';
import {useLocation,useNavigate  } from 'react-router-dom';
import TicketService from '../services/TicketService';
import UserService from '../services/UserService';
import EventService from '../services/EventService';
import TheatreService from '../services/TheatreService';
import './PurchaseTicket.css'

const Purchase = () => {
const location = useLocation();
const { selectedTickets, userId, eventId, theatreId } = location.state || {};
  const [email, setEmail] = useState('');
  const [event, setEvent] = useState(null);
  const [user, setUser] = useState(null);
  const [theatre, setTheatre] = useState(null);
  const [error, setError] = useState(null);
  const selectedTicketsArray = JSON.parse(decodeURIComponent(selectedTickets));
  const navigate = useNavigate();


  useEffect(() => {
    const fetchEventData = async () => {
      try {
        const eventData = await EventService.getEvent(eventId);
        setEvent(eventData);
      } catch (error) {
        setError(error);
      }
    };

    const fetchUserData = async () => {
      try {
        const userDetails = await UserService.getUser(userId);
        setUser(userDetails);
      } catch (error) {
        setError(error);
      }
    };
    const fetchTheatreData = async () => {
        try {
          const theatreDetails = await TheatreService.getTheatre(theatreId);
          setTheatre(theatreDetails);
        } catch (error) {
          setError(error);
        }
      };

    fetchEventData();
    fetchUserData();
    fetchTheatreData();
  }, [eventId, userId,theatreId]);
  const handlePurchase = async () => {
    try {
        const filteredSelectedTickets = selectedTicketsArray.filter(ticket => ticket.tickets > 0);

        // Calculate the total amount of tickets
        const amountTicket = filteredSelectedTickets.reduce((acc, ticket) => acc + ticket.tickets, 0);
        const zoneId = filteredSelectedTickets.reduce((acc,ticket) => acc + ticket.zoneId.zoneId);
        // Calculate the total price
        const totalPrice = filteredSelectedTickets.reduce((acc, ticket) => acc + (ticket.tickets * ticket.price), 0);
        const purchaseRequests = filteredSelectedTickets.map(ticket => ({
            eventId,
            amountTicket: ticket.tickets,
            userId,
            theatreId,
            time: event.event.time,
            date: event.event.date,
            price: ticket.price, // price per ticket since the backend seems to expect this
            zoneId: ticket.zoneId,
          }));

          await Promise.all(purchaseRequests.map(async (purchaseRequest) => {
            await TicketService.purchaseTicket(purchaseRequest);
          }));
      navigate('/confirm');
      // Handle successful purchase (update UI, show confirmation, etc.)
    } catch (error) {
      // Handle errors (show error message to the user)
      console.error('Error during ticket purchase:', error);
    }
  };
  if (!event || !user || !theatre) {
    return <div>Loading...</div>;
  }
  return (
    <div className="purchase-container">
      <div className="purchase-image">
        <div className="image-box">
          <img src={event.event.image[0]?.url} alt={event.event.name} />
        </div>
      </div>
      <div className="purchase-info">
        <h3>{event.event.name}</h3>
        <p><span className="eventDetails-label">Location:</span> {theatre.country}, {theatre.city}, {theatre.name}</p>
        <p><span className="eventDetails-label">Date:</span> {event.event.date}, {event.event.time}</p>
      
        <p><span className="eventDetails-label">Selected Tickets:</span><ul>
        {selectedTicketsArray
            .filter(ticket => ticket.tickets > 0) // Only include zones with more than zero selected tickets
            .map((ticket, index) => {
            // Calculate the total price for the current ticket type
            const totalPrice = ticket.tickets * ticket.price;
            return (
                <li key={index}>
                Zone: {ticket.zoneId}, Tickets: {ticket.tickets}, Total Price: {totalPrice.toFixed(2)}€
                </li>
            );
            })}
        </ul></p>
        
        <p><span className="eventDetails-label">Email:</span> {user.user.email}</p>
        <button className="eventDetails-button" type="button" onClick={handlePurchase}>Purchase</button>
      </div>
    </div>
   );
    
};

export default Purchase;
