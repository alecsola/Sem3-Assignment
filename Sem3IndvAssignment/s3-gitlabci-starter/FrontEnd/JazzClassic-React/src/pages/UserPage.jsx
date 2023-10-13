import React, { useState } from 'react';
import { GetUserComponent } from '../components/GetUsers';
import { CreateUserComponent } from '../components/CreateUser';

const UserPage = () => {
  const [userCreated, setUserCreated] = useState();

  const handleUserCreated = () => {
    setUserCreated(true);
  
  };

  return (
    <div>
      <h1>User Page</h1>
      <CreateUserComponent onUserCreated={handleUserCreated}/>
      <GetUserComponent userCreated={userCreated}/>
    </div>
  );
};

export default UserPage;