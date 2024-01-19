import React, { useState } from 'react';
import './LoginPage.css';
function LoginForm(props) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
  
    const handleSubmit = (event) => {
      event.preventDefault();
      props.onLogin(username, password);
    }
  
    return (
      <div className="LogIn">
      <form onSubmit={handleSubmit}>  
      <div className="LogIn-inputs">
      <h1>Log In</h1>
      
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Username"
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
        />
        <button type="submit">Login</button>
      
        </div>
      </form>
      </div>
    );
  }

  export default LoginForm;