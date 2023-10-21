import React, { useState, useEffect } from 'react';

const GetUserComponent = ({ userCreated }) => {
  const [users, setUsers] = useState([]); // Initialize as an empty array
  const [loadingUsers, setLoadingUsers] = useState(false);
  const [errorUsers, setErrorUsers] = useState(null);

  const getUsers = async () => {
    setLoadingUsers(true);
    setErrorUsers(null);
  
    try {
        const response = await fetch('http://localhost:8080/user');
      
        if (response.ok) {
          const data = await response.json();
      
          if (Array.isArray(data.users)) {
            setUsers(data.users);
          } else {
            setErrorUsers('Invalid data format received from the server');
            console.error('Invalid data format received from the server:', data);
          }
        } else {
          const errorMessage = await response.text();
          setErrorUsers(errorMessage);
          console.error('Failed to fetch users:', errorMessage);
        }
      
      } catch (error) {
        setErrorUsers('An unexpected error occurred while fetching users');
        console.error('Error fetching users:', error);
      } finally {
        setLoadingUsers(false);
      }
  };

    useEffect(() => {
    getUsers();
  }, [userCreated]);    

  return (
    <div>
      <h2>Users</h2>
      {loadingUsers && <p>Loading users...</p>}
      {errorUsers && <p style={{ color: 'red' }}>{errorUsers}</p>}
      <ul>
        {users.map((user) => (
          <li key={user.id}>{user.username} - {user.email}</li>
        ))}
      </ul>
    </div>
  );
};

export { GetUserComponent };`

`