import React, { useState } from 'react';
import { LoginComponent } from '../components/Login'; 
import { UpdateUserPage } from './UpdateUserPage';



const LoginPage = () => {
  const [isLoggedIn, setLoggedIn] = useState(false);
  const [userId, setUserId] = useState(null);
  
  const handleLogin = (userData) => {
    setLoggedIn(true);
    setUserId(userData.userId);
    
    localStorage.setItem('userId', userData.userId);
    
  };  

  return (
    <div>
      <h1>Login Page</h1>
      {!isLoggedIn ? (
        <LoginComponent onLogin={(userData) => handleLogin(userData)} />
      ) : (
        // Pass the user ID to UpdateUserPage
        
        <UpdateUserPage userId={localStorage.getItem('userId')} />
        
      )}
    </div>
  );
};

export { LoginPage };