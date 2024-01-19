const MessageReceived = ({ from, text, direct, username }) => {
    const displayName = from === username ? 'Me' : from;
    return (
      <div>
        <b>{displayName}</b>: {text} {direct ? <b>(direct)</b> : ''}
      </div>
    );
   };

   const ChatMessagesPlaceholder = (props) => {
    return (
        <>
          {props.messagesReceived.map((message, index) => (
            <div key={`${message.id}_${index}`} className="chat-message">
              <MessageReceived
                from={message.from}
                direct={message.to === props.username}
                text={message.text}
                isOwnMessage={message.from === props.username}
                username={props.username}
              />
            </div>
          ))}
        </>
      );
  };

export default ChatMessagesPlaceholder;