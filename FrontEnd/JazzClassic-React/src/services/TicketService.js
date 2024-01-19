import axios from 'axios';
import TokenManager from "./TokenManager";

const BASE_URL = 'http://localhost:8080/ticket';
function createFormData(request){
  const formData = new FormData();
  formData.append('date', request.date);
  formData.append('time', request.time);
  formData.append('price', request.price);
  formData.append('theatreId', request.theatreId);
  formData.append('eventId', request.eventId);
  formData.append('zoneId', request.zoneId);
  formData.append('userId', request.userId);
  formData.append('amountTicket', request.amountTicket);
  return formData
}

async function purchaseTicket(request) {
    try {
        const token = TokenManager.getAccessToken();
        const formData = createFormData(request);
        const response = await axios.post(`${BASE_URL}/purchase`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: `Bearer ${token}`
          },
         });
  
      if (response.ok) {
        console.log('Ticket purchased successfully');
      } else {
        console.error('Ticket purchase failed:', response.statusText);
      }
    } catch (error) {
      console.error('Error during ticket purchase:', error);
      throw error;
    }
  }

export default{
  purchaseTicket,
}