import React from 'react';
import './App.css';
import CalculationPage from './pages/CalculationPage';
import HomePage from './pages/HomePage';
import Login from './pages/LoginPage';

function App() {
  const isLoggedIn = true;
  return (
    <div className="App">
      {
        isLoggedIn 
        ? <CalculationPage />
        : <Login />
      }
    </div>
  );
}

export default App;
