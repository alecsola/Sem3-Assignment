import './App.css';
import { Link } from "react-router-dom";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import CreateTheatrePage from './pages/CreateTheatrePage';
import { UpdateUserPage } from './pages/UpdateUserPage';
import HomePage from './pages/HomePage'
import AuthAPI from './services/AuthAPI';
import UserService from './services/UserService';
import UserAPI from './services/UserAPI';
import React, { useState, useEffect,useMemo } from 'react';
import TokenManager from './services/TokenManager';
import LoginForm from './pages/LoginPage';
import SignUp from './pages/SignUpPage';
import { useAuth } from './AuthContext';
import UpdateEventPage from './pages/UpdateEventPage';
import CreateEventPage from './pages/CreateEventPage';
import EventPage from './pages/EventPage';
import { Client } from '@stomp/stompjs';
import { v4 as uuidv4 } from 'uuid';
import SendMessagePlaceholder from './components/SendMessagePlaceholder';
import UsernamePlaceholder from './components/UsernamePlaceholder';
import ChatMessagesPlaceholder from './components/ChatMessagePlaceholder';
import CreateAdmin from './components/RegisterAdmin';
import EventDetails from './components/EventDetails';
import Purchase from './components/PurchaseTicket';
import './Chat.css';
import UserContext from './UserContext'; 
import ConfirmPage from './pages/ConfirmPage';

function App() {
  const [stompClient, setStompClient] = useState();
  const [username, setUsername] = useState();
  const [messagesReceived, setMessagesReceived] = useState([]);
  const { claims, setClaims, role, updateRole } = useAuth();
  const [userDetails, setUserDetails] = useState(null);
  const [userId, setUserId] = useState(null);

  const setupStompClient = (username) => {
    // stomp client over websockets
    const stompClient = new Client({
      brokerURL: 'ws://localhost:8080/ws',
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });
   
    stompClient.onConnect = () => {
      // subscribe to the backend public topic
      stompClient.subscribe('/topic/publicmessages', (data) => {
        console.log(data);
        onMessageReceived(data,username);
      });
   
      // subscribe to the backend "private" topic
      stompClient.subscribe(`/user/${username}/queue/inboxmessages`, (data) => {
        onMessageReceived(data,username);
      });
    };
   
    // initiate client
    stompClient.activate();
   
    // maintain the client for sending and receiving
    setStompClient(stompClient);
   };
   
   const sendMessage = (newMessage) => {
    const messagePayload = { 'id': uuidv4(), 'from': username, 'text': newMessage.text };
    if (!username) {
      console.error('Username is not defined');
      return;
    }
    if (newMessage.to) {
      const token = TokenManager.getAccessToken();
      // If 'to' field is present, it's a private message, so we use the private endpoint
      const privateMessagePayload = { ...messagePayload, 'to': newMessage.to };
  
      // Add the user's own private message to the messagesReceived state
      setMessagesReceived(messagesReceived => [...messagesReceived, privateMessagePayload]);
  
      fetch('http://localhost:8080/notifications/private', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json', Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(privateMessagePayload),
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to send private message');
          }
        })
        .then(() => console.log('Private message sent successfully:', privateMessagePayload))
        .catch(error => {
          console.error('Error sending private message:', error);
        });
    } else {
      // If 'to' field is not present, it's a public message, so we use STOMP
      stompClient.publish({ 'destination': '/topic/publicmessages', body: JSON.stringify(messagePayload) });
      // Add the sent message to the messagesReceived state
      setMessagesReceived(messagesReceived => [...messagesReceived, messagePayload]);
    }
  };
  const onMessageReceived = (data,username) => {
    const message = JSON.parse(data.body);
    if (!username) {
      console.error('Username is not defined');
      return;
    }
    if (message.to) {
      // It's a private message
      if (message.from === username|| message.to === username) {
        setMessagesReceived(messagesReceived => [...messagesReceived, message]);
      }
      else{
        console.error('user might be undefined',error);
    }
    } else {
      // It's a public message
      setMessagesReceived(messagesReceived => [...messagesReceived, message]);
    }
  };

  const onUsernameInformed = (username) => {
    setUsername(username);
    setupStompClient(username);
  }

  function WelcomePage({ username, onMessageSend, messagesReceived }) {

    
    return (
      <div className="chat-container">
        <h2 className="welcome-header">Welcome, {username}!</h2>
        <div className="destination-input-container">
          <SendMessagePlaceholder username={username} onMessageSend={onMessageSend} />
        </div>
        <div className="messages-container">
          <ChatMessagesPlaceholder username={username} messagesReceived={messagesReceived} />
        </div>
      </div>
    );
  }


  const handleLogin = (username, password) => {
    AuthAPI.login(username, password)
      .catch(() => alert("Login failed!"))
      .then(claims => setClaims(claims))
      .then(getUser)
      .then(updateRole)
      .catch(error => console.error(error));
  }
  const handleSignUp = (name, username, email, password) => {
    UserAPI.createUser({
      name:name,
      username: username,
      email: email,
      password: password
    });
   };

  
   const getUser = async () => {
    const claims = TokenManager.getClaims();
    if (claims?.roles?.includes('CUSTOMER') || claims?.roles?.includes('ADMIN')) {
      const userId = claims?.userId;
      if (userId) {
        try {
          const userDetails = await UserService.getUser(userId);
          const username = userDetails?.user?.username;
      
          if (username) {
            // Now you have the username, and you can use it in your application
            setUsername(username);
            setupStompClient(username);
            setUserId(userId);
          }
        } catch (error) {
          console.error('Error fetching user details:', error);
        }
      }
    }
  };

  const handleLogout = () => {
    TokenManager.clear();
    setClaims(null);
    setUserDetails(null);
    setRole(null);
  }
  function CreateTheatre() {
    const userRole = TokenManager.getClaims()?.roles?.[0];
    if (userRole === 'ADMIN') {
      return(
        <>
          <CreateTheatrePage />
        </>
      );
    }  else {
      // Handle other cases if needed
      return null;
    }
  }
  function UpdateEvent() {
    const userRole = TokenManager.getClaims()?.roles?.[0];
    if (userRole === 'ADMIN') {
      return <UpdateEventPage />;
    } else {
      // Handle other cases if needed
      return null;
    }
  }

  function CreateEvent() {
    const userRole = TokenManager.getClaims()?.roles?.[0];
    if (userRole === 'ADMIN') {
      return <CreateEventPage />;
    } else {
      // Handle other cases if needed
      return null;
    }
  }
  useEffect(() => {
    getUser();
    updateRole();
  },[claims]);
  const contextValue = useMemo(() => ({
    userId,
    setUserId,
  }), [userId]);
  return (
    <UserContext.Provider value={contextValue}>
    <div className="App">
     <nav>
    
     {claims ? null : <a href="/sign">Sign Up</a>}
     <a href="/Event"> Events</a>
     <a href ="/chat">Chat</a>
     {role === 'ADMIN' && <a href="/createTheatre">Theatre Management</a>}
     {role === 'ADMIN' && <a href="/updateEvent">Event Management</a>}
     {role === 'ADMIN' && <a href="/createEvent">Create Event</a>}
     {role === 'ADMIN' && <a href="/createAdmin">User Management</a>}
    <a href="/login">{claims ? 'Logout' : 'Login'}</a>
     </nav>
     <Router>
       <Routes>
         <Route path="/home" element={<HomePage />} />
         <Route path="/Event" element = {<EventPage/>}/>
         <Route path="/sign" element={<SignUp onSignUp={handleSignUp}/>} />
         <Route path="/createTheatre" element={<CreateTheatre />}/>
         <Route path="/updateEvent" element={<UpdateEvent/>}/>
         <Route path="/createAdmin" element={<CreateAdmin/>}/>
         <Route path="/event-details/:eventId" element={<EventDetails />} />
         <Route path="/purchase" element={<Purchase/>}/>
          <Route
            path="/chat"
            element={<WelcomePage username={username} onMessageSend={sendMessage} messagesReceived={messagesReceived} />}
          />
         <Route path="/createEvent" element={<CreateEvent/>}/>
         <Route path="/confirm" element={<ConfirmPage/>}/>

         <Route path="/login" element={
           claims ? (
             <div>
               <p>Welcome, {claims.sub}</p>
               <button onClick={handleLogout}>Logout</button>
               <br />
               <a href='/' target='_blank'>Open in new Tab</a>
             </div>
           ) : (
             <LoginForm onLogin={handleLogin} />
           )
         } />
         <Route path="/update" element={<UpdateUserPage />} />
       </Routes>
     </Router>
   </div>
   </UserContext.Provider>
  );
}
export default App;
