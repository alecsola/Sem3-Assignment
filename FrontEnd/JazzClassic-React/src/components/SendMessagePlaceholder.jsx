import React, { useState } from "react";

const SendMessagePlaceholder = (props) => {
    const [message, setMessage] = useState('');
    const [destinationUsername, setDestinationUsername] = useState('');
  
    if (!props.username) {
      return <></>;
    }
  
    const onMessageSend = () => {
      if (!destinationUsername) {
        alert('Please provide a destination username!');
        return;
      }
  
      if (!message) {
        alert('Please type a message!');
        return;
      }
  
      props.onMessageSend({ 'text': message, 'to': destinationUsername });
      setMessage('');
    }
  
    return (
        <div className="message-input-container">
             <input className="input-field-chat-destination" id='destUsername' type='text' onChange={(event) => setDestinationUsername(event.target.value)} placeholder="Destination Username" />
          <input className="input-field-chat-message" id='message' type='text' onChange={(event) => setMessage(event.target.value)} value={message} placeholder="Type your message" />
          <button className="send-button" onClick={onMessageSend}>Send</button>
        </div>
      );
  }
  
  export default SendMessagePlaceholder;