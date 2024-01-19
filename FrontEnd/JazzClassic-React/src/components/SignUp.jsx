import React, { useState } from 'react';
import UserAPI from '../services/UserAPI';
import UserService from '../services/UserService';
import './RegisterAdmin.css'
const SignUpComponent = () => {
  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSignUp = () => {
    const signUpData = {
      name,
      username,
      email,
      password,
    };

    const doSignUp = async () => {
      try {
        await UserService.createUser(signUpData);
        console.log('Sign up successful');
      } catch (error) {
        console.error('Sign up failed:', error);
      }
    };

    // Call the async function
    doSignUp();
  };

  return (
    <div className='SignUp'>
      <h2>Sign Up</h2>
      <form>
      <div className="SignUp-inputs">
        <label>Name:</label>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

        <label>Username:</label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />

        <label>Email:</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />

        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

        <button type="button" onClick={handleSignUp}>Sign Up</button>
        </div>
      </form>
    </div>
  );
};

export default SignUpComponent;