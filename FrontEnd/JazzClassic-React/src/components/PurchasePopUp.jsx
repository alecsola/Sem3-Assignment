import React, { useState } from 'react';
import './PurchasePopUp.css'
const PurchasePopUp = ({ zones, onClose, onConfirm }) => {
  const [selectedTickets, setSelectedTickets] = useState(
    zones.map(zone => ({ zoneId: zone.id, tickets: 0, price: zone.price }))
   );

      const handleTicketChange = (zoneId, tickets) => {
        setSelectedTickets(prevSelectedTickets =>
          prevSelectedTickets.map(ticket =>
            ticket.zoneId === zoneId ? { ...ticket, tickets: parseInt(tickets, 10) } : ticket
          )
        );
      };
  const handleConfirm = () => {
    // Pass the selectedTickets to the parent component
    const filteredSelectedTickets = selectedTickets.filter(ticket => ticket.tickets > 0);
    onConfirm(JSON.stringify(filteredSelectedTickets));
  };

  return (
    <div className="purchase-modal">
      <h2>Select Number of Tickets</h2>
      {zones.map(zone => (
        <div key={zone.id}>
          <p>Zone {zone.id}, Available Seats: {zone.availableSeats}</p>
          <label>
            Number of Tickets:
            <input
              type="number"
              value={selectedTickets.find(ticket => ticket.zoneId === zone.id).tickets}
              min={0}
              max={zone.availableSeats}
              onChange={e => handleTicketChange(zone.id, e.target.value)}
            />
          </label>
        </div>
      ))}
      <div className='buttonPopUp'>
      <button onClick={handleConfirm}>Go to Purchase</button>
      </div>
      <div className='buttonCancelPopUp'>
      <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
};

export default PurchasePopUp;