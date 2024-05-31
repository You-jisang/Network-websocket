import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8080/ws');

    ws.onopen = () => {
      console.log('Connected to the server');
    };

    ws.onmessage = (event) => {
      setMessages(prevMessages => [...prevMessages, event.data]);
    };

    ws.onclose = () => {
      console.log('Disconnected from the server');
    };

    return () => {
      ws.close();
    };
  }, []);

  const sendMessage = () => {
    const ws = new WebSocket('ws://localhost:8080/ws');
    ws.onopen = () => {
      ws.send(input);
      setInput('');
    };
  };

  return (
      <div className="App">
        <h1>WebSocket 369 Game</h1>
        <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="숫자를 입력하세요"
        />
        <button onClick={sendMessage}>전송</button>
        <ul>
          {messages.map((message, index) => (
              <li key={index}>{message}</li>
          ))}
        </ul>
      </div>
  );
}

export default App;
