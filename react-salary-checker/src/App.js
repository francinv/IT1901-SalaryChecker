import React from 'react';
import './App.css';
import CalculationPage from './pages/CalculationPage';
import HomePage from './pages/HomePage';
import Login from './pages/LoginPage';
import SalariesPage from './pages/SalariesPage';

function App() {
  const isLoggedIn = true;
  return (
    <div className="App">
      {
        isLoggedIn 
        ? <SalariesPage />
        : <Login />
      }
    </div>
  );
}

export default App;
