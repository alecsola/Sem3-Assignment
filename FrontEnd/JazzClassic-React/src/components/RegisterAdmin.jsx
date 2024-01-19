import React, { useState } from 'react';
import axios from 'axios';
import UserService from '../services/UserService';
import './RegisterAdmin.css'
function CreateAdmin() {
   const [username, setUsername] = useState('');
   const [email, setEmail] = useState('');
   const [password, setPassword] = useState('');
   const [name, setName] = useState('');
 
   const handleSubmit = async (event) => {
    event.preventDefault();
    
    const userData = {
      name: name,
      username: username,
      email: email,
      password: password,
    };

    try {
      const response = await UserService.createAdmin(userData);
      console.log('User Created:', response.data);
      // You may also handle the success in the props.onSignUp callback if needed
      props.onSignUp(response.data);
    } catch (error) {
      console.error('Error creating user:', error);
      // Handle error as needed
    }
  }
 
   return (
     <div className='SignUp'>
       <form onSubmit={handleSubmit}> 
       <div className="SignUp-inputs">
         <h1>Create Administrator</h1>
         <input
           type="text"
           value={name}
           onChange={(e) => setName(e.target.value)}
           placeholder="Name"
         />
         <input
           type="text"
           value={username}
           onChange={(e) => setUsername(e.target.value)}
           placeholder="Username"
         />
         <input
           type="text"
           value={email}
           onChange={(e) => setEmail(e.target.value)}
           placeholder="Email"
         />
         <input
           type="password"
           value={password}
           onChange={(e) => setPassword(e.target.value)}
           placeholder="Password"
         />
         <button type="submit">Sign Up</button>
         </div>
       </form>
     </div>
   );
 }

export default CreateAdmin;