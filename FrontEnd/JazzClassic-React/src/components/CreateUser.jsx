  import React, { useState} from 'react';

  const CreateUserComponent = ({ onUserCreated }) => {
    const [users, setUsers] = useState([]);
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('')
    
    const handleCreateUser = async () => {
      try {
        const response = await fetch('http://localhost:8080/user', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            name: name,
            username: username,
            email: email,
            password: password,

          }),
        });

        if (response.ok) {
          const data = await response.json();
          console.log('User created successfully:', data);
          onUserCreated();

        } else {
          console.error('Failed to create user:', response.statusText);
        }
      } catch (error) {
        console.error('Error creating user:', error);
      }
    };
  
    return (
      <div>
        <h2>Create User</h2>
        <label>Name: </label>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        <br />
        <label>Username: </label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        <br />
        <label>Email: </label>
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        <br />
        <label> Password: </label>
        <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} />
        <br />
        <button onClick={handleCreateUser}>Create User</button>
      </div>
    );
  };



  export { CreateUserComponent };