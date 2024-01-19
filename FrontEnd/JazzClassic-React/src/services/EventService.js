import axios from 'axios';
import TokenManager from "./TokenManager";
import TheatreService from './TheatreService';
const BASE_URL = 'http://localhost:8080/event';
const IMAGE_CONT = 'http://localhost:8080/image';
function createFormData(eventData){
    const formData = new FormData();
    formData.append('name', eventData.name);
    formData.append('theatreId', eventData.theatreId);
    formData.append('date', eventData.date);
    formData.append('time', eventData.time);
    formData.append('zones', JSON.stringify(eventData.zones));
    for (let i = 0; i < eventData.image.length; i++) {
      formData.append('image', eventData.image[i]);
    }
    return formData;
  }

  async function createEvent(eventData) {
    const token = TokenManager.getAccessToken(); 
    const formData = createFormData(eventData);
    
      const response = await axios.post(`${BASE_URL}/create`, formData, { 
          
            headers: {'Content-Type': 'multipart/form-data', Authorization: `Bearer ${token}` }
      })
      .then(response =>response.data)
          .catch(error => {
            console.error('Error creating post:', error);
            throw error; 
          });
  }


  async function filterEvent(name) {
    try {
      name = name.trim() === '' ? null : name;
      const token = TokenManager.getAccessToken();
      const response = await axios.get(`${BASE_URL}/filter`, {
        params: {
          name: name,
        },
        headers: { Authorization: `Bearer ${token}` }
      });
  
      const responseData = response.data;
      let events = []; 
      console.log(responseData); 
      // Check if theatres is an array before iterating
      if ( Array.isArray(responseData.event)){
        const events = responseData.event;
        for (let event of events) {
          if (event.image && event.image.length > 0) {
            const updatedImages = [];
  
            for (let image of event.image) {
              try {
                const imageResponse = await axios.get(`${IMAGE_CONT}/event/${event.id}/${image.url}`, {
                  headers: { Authorization: `Bearer ${token}` },
                  responseType: 'blob'
                 });
  
                const blob = new Blob([imageResponse.data], { type: image.type });
                const objectURL = URL.createObjectURL(blob);
  
                const updatedContentItem = {
                  url: objectURL,
                  type: image.type, 
                };
  
                updatedImages.push(updatedContentItem);
              } catch (error) {
                console.error(`Failed to retrieve file for theatre ${event.id}: ${image.fileUrl}`);
              }
            }
  
            event.image = updatedImages; // Fix the variable name here
          }
        }
        return events;
      }
      
    } catch (error) {
      console.error('Error fetching theatre data:', error);
      throw error;
    }
  }


  async function findAll() {
    try {
      
      const token = TokenManager.getAccessToken();
      const response = await axios.get(`${BASE_URL}/findAll`, {
        headers: { Authorization: `Bearer ${token}` }
      });
  
      const responseData = response.data;
      let events = []; 
      // Check if theatres is an array before iterating
      if (Array.isArray(responseData.event)) {
        const events = responseData.event;
        for (let event of events) {
          if (event.image && event.image.length > 0) {
            const updatedImages = [];
  
            for (let image of event.image) {
              try {
                const imageResponse = await axios.get(`${IMAGE_CONT}/event/${event.id}/${image.url}`, {
                  headers: { Authorization: `Bearer ${token}` },
                  responseType: 'blob'
                 });
  
                const blob = new Blob([imageResponse.data], { type: image.type });
                const objectURL = URL.createObjectURL(blob);
  
                const updatedContentItem = {
                  url: objectURL,
                  type: image.type, 
                };
  
                updatedImages.push(updatedContentItem);
              } catch (error) {
                console.error(`Failed to retrieve file for theatre ${event.id}: ${image.fileUrl}`);
              }
            }
  
            event.image = updatedImages; // Fix the variable name here
          }
        }
        return events;
      }
      
    } catch (error) {
      console.error('Error fetching theatre data:', error);
      throw error;
    }
  }
  async function getNotCompleted() {
    try {
      
      const token = TokenManager.getAccessToken();
      const response = await axios.get(`${BASE_URL}/getNotCompleted`, {
        headers: { Authorization: `Bearer ${token}` }
      });
  
      const responseData = response.data;
      let events = []; 
      // Check if theatres is an array before iterating
      if (Array.isArray(responseData.event)) {
        const events = responseData.event;
        for (let event of events) {
          if (event.image && event.image.length > 0) {
            const updatedImages = [];
  
            for (let image of event.image) {
              try {
                const imageResponse = await axios.get(`${IMAGE_CONT}/event/${event.id}/${image.url}`, {
                  headers: { Authorization: `Bearer ${token}` },
                  responseType: 'blob'
                 });
  
                const blob = new Blob([imageResponse.data], { type: image.type });
                const objectURL = URL.createObjectURL(blob);
  
                const updatedContentItem = {
                  url: objectURL,
                  type: image.type, 
                };
  
                updatedImages.push(updatedContentItem);
              } catch (error) {
                console.error(`Failed to retrieve file for theatre ${event.id}: ${image.fileUrl}`);
              }
            }
  
            event.image = updatedImages; // Fix the variable name here
          }
        }
        return events;
      }
      
    } catch (error) {
      console.error('Error fetching theatre data:', error);
      throw error;
    }
  }

  async function getByPopularity() {
    try {
      
      const token = TokenManager.getAccessToken();
      const response = await axios.get(`${BASE_URL}/getByPopularity`, {
        headers: { Authorization: `Bearer ${token}` }
      });
  
      const responseData = response.data;
      let events = []; 
      // Check if theatres is an array before iterating
      if (Array.isArray(responseData.event)) {
        const events = responseData.event;
        for (let event of events) {
          if (event.image && event.image.length > 0) {
            const updatedImages = [];
  
            for (let image of event.image) {
              try {
                const imageResponse = await axios.get(`${IMAGE_CONT}/event/${event.id}/${image.url}`, {
                  headers: { Authorization: `Bearer ${token}` },
                  responseType: 'blob'
                 });
  
                const blob = new Blob([imageResponse.data], { type: image.type });
                const objectURL = URL.createObjectURL(blob);
  
                const updatedContentItem = {
                  url: objectURL,
                  type: image.type, 
                };
  
                updatedImages.push(updatedContentItem);
              } catch (error) {
                console.error(`Failed to retrieve file for theatre ${event.id}: ${image.fileUrl}`);
              }
            }
  
            event.image = updatedImages; // Fix the variable name here
          }
        }
        return events;
      }
      
    } catch (error) {
      console.error('Error fetching theatre data:', error);
      throw error;
    }
  }

  async function getEvent(eventId) {
    try {
      const token = TokenManager.getAccessToken();
      const response = await axios.get(`${BASE_URL}/getId/${eventId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
  
      const eventData = response.data;
      console.log("eventData:", eventData);
      if (eventData.event.image && eventData.event.image.length > 0) {
        const updatedImages = [];
  
        for (let image of eventData.event.image) {
          try {
            const imageResponse = await axios.get(`${IMAGE_CONT}/event/${eventId}/${image.url}`, {
              headers: { Authorization: `Bearer ${token}` },
              responseType: 'blob',
            });
  
            const blob = new Blob([imageResponse.data], { type: image.type });
            const objectURL = URL.createObjectURL(blob);
  
            const updatedContentItem = {
              url: objectURL,
              type: image.type,
            };
  
            updatedImages.push(updatedContentItem);
          } catch (error) {
            console.error(`Failed to retrieve file for event ${eventId}: ${image.url}`);
          }
        }
  
        eventData.event.image = updatedImages;
      }
  
      return eventData;
    } catch (error) {
      console.error('Error fetching event data:', error);
      throw error;
    }
  }
  const deleteImage = async (eventId) => {
    try {
        const token = TokenManager.getAccessToken();
        await axios.delete(`${IMAGE_CONT}/${eventId}`, {
            headers: { Authorization: `Bearer ${token}` },
        });
    } catch (error) {
        console.error(`Error deleting image ${imageName} for event ${eventId}:`, error);
        throw error;
    }
};
  const updateEvent = async (id,eventData) => {
    try {
        const token = TokenManager.getAccessToken();
        const formData = new FormData();
            formData.append('id', id);
            formData.append('name', eventData.name);
            for (let i = 0; i < eventData.image.length; i++) {
              formData.append('image', eventData.image[i]);
            }
            formData.append('theatreId', eventData.theatreId);
            formData.append('date', eventData.date);
            formData.append('time', eventData.time);
            formData.append('completed',eventData.completed)
            formData.append('zones', JSON.stringify(eventData.zones));
            
            await deleteImage(id).catch(error => {
              console.error('Error deleting image:', error);
              // Handle the error or throw it again if needed
              throw error;
          });
        
          
        const response = await axios.post(`${BASE_URL}/update/${id}`, formData, { 
          
          headers: {'Content-Type': 'multipart/form-data', Authorization: `Bearer ${token}` }
    })
    .then(response =>response.data)
        .catch(error => {
          console.error('Error creating post:', error);
          throw error; 
        });
  
      const { event } = response.data;
      if (event.image && event.image.length > 0) {
        const updatedImages = [];

        for (let image of event.image) {
          try {
            const imageResponse = await axios.get(`${IMAGE_CONT}/image/event/${event.id}/${image.url}`, {
              headers: { Authorization: `Bearer ${token}` },
              responseType: 'blob'
             });

            const blob = new Blob([imageResponse.data], { type: image.type });
            const objectURL = URL.createObjectURL(blob);

            const updatedContentItem = {
              url: objectURL,
              type: image.type, 
            };

            updatedImages.push(updatedContentItem);
          } catch (error) {
            console.error(`Failed to retrieve file for theatre ${event.id}: ${image.fileUrl}`);
          }
        }

        event.image = updatedImages; // Fix the variable name here
      }

      return event;
    } catch (error) {
      console.error("Error updating user details:", error);
      throw error;
    }
  };


  const deleteEvent = async (id,eventData) => {
    try {
        const token = TokenManager.getAccessToken();
        const formData = new FormData();
            formData.append('id', id);
            formData.append('name', eventData.name);
            for (let i = 0; i < eventData.image.length; i++) {
              formData.append('image', eventData.image[i]);
            }
            formData.append('theatreId', eventData.theatreId);
            formData.append('date', eventData.date);
            formData.append('time', eventData.time);
            formData.append('completed',eventData.completed)
            formData.append('zones', JSON.stringify(eventData.zones));
            
    
           await axios.post(`${BASE_URL}/delete/${id}`, formData, 
           { 
                   headers: {'Content-Type': 'multipart/form-data', Authorization: `Bearer ${token}` }
            })

    } catch (error) {
      console.error("Error deleting user details:", error);
      throw error;
    }
  };

  

  export default{
    createEvent,
    updateEvent,
    filterEvent,
    getNotCompleted,
    findAll,
    getEvent,
    deleteEvent,
    getByPopularity,
  }