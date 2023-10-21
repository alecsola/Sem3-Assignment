import React from 'react';
import { UpdateUserComponent } from '../components/UpdateUser'; // Adjust the path accordingly

const UpdateUserPage = ({ userId }) => {
  return (
    <div>
      <h1>Update User Page</h1>
      <UpdateUserComponent userId={userId} />
    </div>
  );
};

export { UpdateUserPage };