import axios from 'axios';
import TokenManager from "./TokenManager";

const BASE_URL = 'http://localhost:8080/theatre';
const hostname = 'http://localhost:8080';

async function filterTheatre(name, country, city) {
  try {
    name = name.trim() === '' ? null : name;
    country = country.trim() === '' ? null : country;
    city = city.trim() === '' ? null : city;
    const token = TokenManager.getAccessToken();
    const response = await axios.get(`${BASE_URL}/filter`, {
      params: {
        name: name,
        country: country,
        city: city
      },
      headers: { Authorization: `Bearer ${token}` }
    });

    const responseData = response.data;
    let theatres = []; 
    // Check if theatres is an array before iterating
    if (Array.isArray(responseData.theatres)) {
      const theatres = responseData.theatres;
      for (let theatre of theatres) {
        if (theatre.image && theatre.image.length > 0) {
          const updatedImages = [];

          for (let image of theatre.image) {
            try {
              const imageResponse = await axios.get(`${hostname}/image/${theatre.id}/${image.url}`, {
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
              console.error(`Failed to retrieve file for theatre ${theatre.id}: ${image.fileUrl}`);
            }
          }

          theatre.image = updatedImages; // Fix the variable name here
        }
      }
      return theatres;
    }
    
  } catch (error) {
    console.error('Error fetching theatre data:', error);
    throw error;
  }
}
    function createFormData(theatreData){
      const formData = new FormData();
      formData.append('name', theatreData.name);
      formData.append('country', theatreData.country);
      formData.append('city', theatreData.city);
      formData.append('details', theatreData.details);
      formData.append('capacity', theatreData.capacity);
      formData.append('popularity', theatreData.popularity);

      // Append each file to the formData
      for (let i = 0; i < theatreData.image.length; i++) {
        formData.append('image', theatreData.image[i]);
      }
      return formData;
    }

    async function createTheatre(theatreData) {
      const token = TokenManager.getAccessToken(); 
      const formData = createFormData(theatreData);
      
        const response = await axios.post(`${hostname}/theatre`, formData, { 
            
              headers: {'Content-Type': 'multipart/form-data', Authorization: `Bearer ${token}` }
        })
        .then(response =>response.data)
            .catch(error => {
              console.error('Error creating post:', error);
              throw error; 
            });
    }

    async function getTheatre(theatreId) {
      try {
        const token = TokenManager.getAccessToken();
        const response = await axios.get(`${BASE_URL}/getId/${theatreId}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
    
        const theatre = response.data;
        
         
            if (theatre.image && theatre.image.length > 0) {
              const updatedImages = [];
    
              for (let image of theatre.image) {
                try {
                  const imageResponse = await axios.get(`${hostname}/image/${theatre.id}/${image.url}`, {
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
                  console.error(`Failed to retrieve file for theatre ${theatre.id}: ${image.fileUrl}`);
                }
              }
    
              theatre.image = updatedImages; // Fix the variable name here
            
          
          return theatre;
        }
      } catch (error) {
        console.error('Error fetching event data:', error);
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
        let theatres = []; 
        // Check if theatres is an array before iterating
        if (Array.isArray(responseData.theatres)) {
          const theatres = responseData.theatres;
          for (let theatre of theatres) {
            if (theatre.image && theatre.image.length > 0) {
              const updatedImages = [];
    
              for (let image of theatre.image) {
                try {
                  const imageResponse = await axios.get(`${hostname}/image/${theatre.id}/${image.url}`, {
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
                  console.error(`Failed to retrieve file for theatre ${theatre.id}: ${image.fileUrl}`);
                }
              }
    
              theatre.image = updatedImages; // Fix the variable name here
            }
          }
          return theatres;
        }
        
      } catch (error) {
        console.error('Error fetching theatre data:', error);
        throw error;
      }
    }





export default {
    filterTheatre,
    createTheatre,
    findAll,
    getTheatre
}   

