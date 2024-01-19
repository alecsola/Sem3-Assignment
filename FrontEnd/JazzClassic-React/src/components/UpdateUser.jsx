import React, { useState, useEffect } from 'react';
import userService from "../services/UserService.js";

const UpdateUserComponent = ({ userId }) => {
  const [updatedUserData, setUpdatedUserData] = useState({
    userId: '',
    name: '',
    username: '',
    email: '',
    hashedPassword: '',
  });
  const [isEditing, setEditing] = useState(false);

  useEffect(() => {
    if (userId) {
      const fetchUserData = async () => {
        try {
          const data = await userService.getUser(userId);
          setUpdatedUserData(data);
        } catch (error) {
          console.error('Error fetching user data:', error);
        }
      };

      fetchUserData();
    }
  }, [userId]);

  const handleUpdateUser = async () => {
    try {
      await userService.updateUser({
        id: updatedUserData.userId,
        name: updatedUserData.name,
        username: updatedUserData.username,
        email: updatedUserData.email,
        password: updatedUserData.hashedPassword,
      });
    } catch (error) {
      console.error('Error during user update:', error);
    }
  };

  return (
    <div>
      <h2>Update User</h2>
      {isEditing ? (
        // Display input fields when editing
        <div>
          <label>Name: </label>
          <input type="text" value={updatedUserData.name} onChange={(e) => setUpdatedUserData({ ...updatedUserData, name: e.target.value })} />
          <br />
          <label>Username: </label>
          <input type="text" value={updatedUserData.username} onChange={(e) => setUpdatedUserData({ ...updatedUserData, username: e.target.value })} />
          <br />
          <label>Email: </label>
          <input type="text" value={updatedUserData.email} onChange={(e) => setUpdatedUserData({ ...updatedUserData, email: e.target.value })} />
          <br />
         
         
        </div>
      ) : (
        // Display user data as text when not editing
        <div>
          <p>Name: {updatedUserData.name}</p>
          <p>Username: {updatedUserData.username}</p>
          <p>Email: {updatedUserData.email}</p>
       
        </div>
      )}
      <br />
      <button onClick={isEditing ? handleUpdateUser : () => setEditing(true)}>
        {isEditing ? 'Update User' : 'Edit User'}
      </button>
    </div>
  );
};

export { UpdateUserComponent };