import React from 'react';
import './App.css';
import SignInSide from './components/signin';
import HomePage from './pages/HomePage';
import Login from './pages/LoginPage';

function App() {
  const isLoggedIn = true;
  return (
    <div className="App">
      {
        isLoggedIn 
        ? <HomePage />
        : <Login />
      }
    </div>
  );
}

export default App;
