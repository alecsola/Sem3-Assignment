import React, { createContext, useContext, useState } from 'react';
import TokenManager from './services/TokenManager';
const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [claims, setClaims] = useState(TokenManager.getClaims());
  const [role, setRole] = useState(TokenManager.getClaims()?.roles?.[0]);

  const updateRole = () => {
    const userRole = TokenManager.getClaims()?.roles?.[0];
    setRole(userRole);
  };

  const contextValue = {
    claims,
    setClaims,
    role,
    updateRole,
  };

  return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};

export const useAuth = () => useContext(AuthContext);