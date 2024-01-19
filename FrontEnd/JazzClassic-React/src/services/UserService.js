import axios from 'axios';
import TokenManager from './TokenManager';
const BASE_URL = 'http://localhost:8080/user';

async function getUser(userId) {
  try {
    const token = TokenManager.getAccessToken();
    const response = await axios.get(`${BASE_URL}/get/${userId}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching user data:', error);
    throw error;
  }
}
async function createUser(userData){   
  return axios.post(
    `${BASE_URL}/user`, // Adjust the endpoint based on your backend API
    userData,
    {
      headers: {
        'Content-Type': 'application/json',
      },
    }
  );
}
async function createAdmin(userData) {
  const token = TokenManager.getAccessToken();
  return axios.post(
    `${BASE_URL}/admin`,
    userData,
    {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    }
  );
}
async function updateUser(userData) {
  try {
    const response = await axios.post(`${BASE_URL}/updateUser`, userData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (response.ok) {
      console.log('User updated successfully');
    } else {
      console.error('User update failed:', response.statusText);
    }
  } catch (error) {
    console.error('Error during user update:', error);
    throw error;
  }
}

export default {
  getUser,
  updateUser,
  createUser,
  createAdmin,
};