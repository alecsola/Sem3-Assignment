import axios from "axios";
import TokenManager from "./TokenManager";


const baseURL = 'http://localhost:8080';
const UserAPI = {
    createUser: (userData) => {
        
    
        return axios.post(
          `${baseURL}/user`, // Adjust the endpoint based on your backend API
          userData,
          {
            headers: {
              'Content-Type': 'application/json',
            },
          }
        );
      },    
    getUser: (userId) => axios.get(`http://localhost:8080/user/get/${studentId}`,
        {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        .then(response => response.data)

}

export default UserAPI;