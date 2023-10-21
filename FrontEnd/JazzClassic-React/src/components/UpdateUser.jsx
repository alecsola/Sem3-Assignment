import React, { useState, useEffect } from 'react';

const UpdateUserComponent = ({ userId }) => {
  const [updatedUserData, setUpdatedUserData] = useState({
    Name: '',
    Username: '',
    Email: '',
    Password: '',
  });
  const [isEditing, setEditing] = useState(false);
  useEffect(() => {
  if (userId) {
    const fetchUserData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/user/getUserbyId?id=${userId}`);
        const data = await response.json();
        setUpdatedUserData(data);
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };

    fetchUserData();
  }
}, [userId]);
  const handleUpdateUser = async () => {
    // Implement logic to update user data
    try {
      const updateResponse = await fetch('http://localhost:8080/user/updateUser', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userId: userId,
          ...updatedUserData, 
         
        }),
      });

      if (updateResponse.ok) {
        console.log('User updated successfully');
      } else {
        console.error('User update failed:', updateResponse.statusText);
      }
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
          <input type="text" value={updatedUserData.Name} onChange={(e) => setUpdatedUserData({ ...userData, Name: e.target.value })} />
          <br />
          <label>Username: </label>
          <input type="text" value={updatedUserData.Username} onChange={(e) => setUpdatedUserData({ ...userData, Username: e.target.value })} />
          <br />
          <label>Email: </label>
          <input type="text" value={updatedUserData.Email} onChange={(e) => setUpdatedUserData({ ...userData, Email: e.target.value })} />
          <br />
          <label>Password: </label>
          <input type="text" value={updatedUserData.Password} onChange={(e) => setUserData({ ...userData, Password: e.target.value })} />
          <br />
        </div>
      ) : (
        // Display user data as text when not editing
        <div>
          <p>Name: {updatedUserData.Name}</p>
          <p>Username: {updatedUserData.Username}</p>
          <p>Email: {updatedUserData.Email}</p>
          <p>Password: {updatedUserData.Password}</p>
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