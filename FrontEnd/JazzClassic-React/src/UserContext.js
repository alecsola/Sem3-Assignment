import { createContext } from 'react';

export const UserContext = createContext({
    userId: null,
    setUserId: () => {}, // This is an updater function you can use to set the userId
  });

export default UserContext;