import React, { useState } from 'react';
import AuthAPI from '../services/AuthAPI';
import UserAPI from '../services/UserAPI';
import './Login.css';
import { useNavigate } from 'react-router-dom';

const LoginComponent = ({ onLogin}) => {
  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isSignUp, setIsSignUp] = useState(true);
  const navigate = useNavigate();
 const handleLogin = async () => {
  console.log('Username:', username);
  console.log('Password:', password);
  try {
    await AuthAPI.login(username, password);
    console.log('Login successful');
    navigate('/home');
  } catch (error) {
    console.error('Login failed:', error);
  }
};
  const handleSignUp = () => {
    const signUpData = {
      name,
      username,
      email,
      password,
    };

    UserAPI.createUser(signUpData)
      .then(() => {
        // Redirect or perform actions after successful sign up
        console.log('Sign up successful');
      })
      .catch(error => {
        // Handle sign up error
        console.error('Sign up failed:', error);
      });
  };
  

  return (
    <div>
      <div className="container">
      <form className="form-style"><h2>{isSignUp ? 'Sign Up' : 'Login'}</h2>
      <div className="fields">
        {isSignUp && (
          <>
            <label>Name:</label>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

            <label>Email:</label>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </>
        )}

        <label>Username:</label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />

        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        {isSignUp ? (
          
          <button type="button" className="main-button" onClick={handleSignUp}>Sign Up</button>
          
        ) : (
          <button className="main-button" onClick={handleLogin}>Login</button>
        )}

<p className="secondary-button" onClick={() => setIsSignUp(!isSignUp)}>
    {isSignUp ? ' Login here.' : 'Sign up here.'}
  </p>
      </form>
      </div>
    </div>
  );


};

export { LoginComponent };