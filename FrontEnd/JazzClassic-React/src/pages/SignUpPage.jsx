import React, { useState } from 'react';
import "../components/RegisterAdmin.css"

function SignUp(props) {
   const [username, setUsername] = useState('');
   const [email, setEmail] = useState('');
   const [password, setPassword] = useState('');
   const [name, setName] = useState('');
 
   const handleSubmit = (event) => {
     event.preventDefault();
     props.onSignUp(name,username, email, password);
     console.log('User Created');
   }
 
   return (
     <div className='SignUp'>
       <form onSubmit={handleSubmit}> 
       <div className="SignUp-inputs">
         <h1>Sign Up</h1>
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

export default SignUp;